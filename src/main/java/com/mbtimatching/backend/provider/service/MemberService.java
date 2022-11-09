package com.mbtimatching.backend.provider.service;

import com.mbtimatching.backend.core.security.role.Role;
import com.mbtimatching.backend.core.service.MemberServiceInterface;
import com.mbtimatching.backend.entity.Member;
import com.mbtimatching.backend.exception.error.CustomJwtRuntimeException;
import com.mbtimatching.backend.exception.error.LoginFailedException;
import com.mbtimatching.backend.exception.error.RegisterFailedException;
import com.mbtimatching.backend.exception.error.UserDuplicatedException;
import com.mbtimatching.backend.provider.security.JwtAuthToken;
import com.mbtimatching.backend.provider.security.JwtAuthTokenProvider;
import com.mbtimatching.backend.repository.MemberRepository;
import com.mbtimatching.backend.util.SHA256Util;
import com.mbtimatching.backend.web.dto.RequestUser;
import com.mbtimatching.backend.web.dto.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberServiceInterface {
    private final MemberRepository memberRepository;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    @Transactional
    @Override
    public void register(RequestUser.Register registerDto) {
        Member member = memberRepository.findByEmail(registerDto.getEmail());
        if (member != null) {
            // 이미 존재하는 아이디
            throw new RegisterFailedException();
        }
        //salt 생성
        String salt = SHA256Util.generateSalt();
        //sha256으로 솔트와 함께 암호화
        String encryptedPassword = SHA256Util.getEncrypt(registerDto.getPassword(), salt);
        //db에 기입
        member = Member.builder()
                .email(registerDto.getEmail())
                .password(encryptedPassword)
                .salt(salt)
                .gender(registerDto.getGender())
                .mbti(registerDto.getMbti())
                .nickname(registerDto.getNickname())
                .build();
        memberRepository.save(member);
    }

    @Transactional
    @Override
    public Optional<ResponseUser.Login> login(RequestUser.Login loginDto) {
        //아이디로 유저 꺼내기
        Member member = memberRepository.findByEmail(loginDto.getEmail());
        if (member == null) {   //없으면 예외
            throw new LoginFailedException();
        }
        //salt 꺼내기
        String salt = member.getSalt();
        //salt와 loginDto를 조합해서 암호화
        String encryptedPassword = SHA256Util.getEncrypt(loginDto.getPassword(), salt);
        //비교
        member = memberRepository.findByEmailAndPassword(loginDto.getEmail(), encryptedPassword);
        ResponseUser.Login login = null;
        if (member != null) {
            //로그인 성공
            String refreshToken = createRefreshToken(member.getEmail());
            login = ResponseUser.Login.builder()
                    .accessToken(createAccessToken(member.getEmail()))
                    .refreshToken(refreshToken)
                    .build();
            member.updateRefreshToken(refreshToken);  //로그인 할 때 마다 리프레시 토큰 업데이트
        } else {
            throw new LoginFailedException();
        }
        return Optional.ofNullable(login);
    }

    @Transactional
    @Override
    public Optional<ResponseUser.Token> refreshToken(String refreshToken) {
        //request값이 null이면 예외 처리
        if (refreshToken == null) {
            throw new CustomJwtRuntimeException();
        }
        Member member = memberRepository.findByRefreshToken(refreshToken);
        //해당 user테이블이 없으면 예외 처리
        if (member == null) {
            throw new CustomJwtRuntimeException();
        }
        //토큰 유효성 검사
        JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(member.getRefreshToken());
        if (!jwtAuthToken.validate() || !jwtAuthToken.getData().get("role").equals(Role.USER.getCode())) {
            return Optional.empty();
        }
        String accessToken = createAccessToken(member.getEmail()); //액세스토큰 재발급

        ResponseUser.Token newToken = ResponseUser.Token.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        return Optional.ofNullable(newToken);
    }

    @Transactional
    @Override
    public void checkEmail(String id) {
        if (null != memberRepository.findByEmail(id)) {
            throw new UserDuplicatedException();
        }
    }

    @Override
    public String createAccessToken(String id) {
        Date expiredDate = Date.from(LocalDateTime.now().plusMinutes(30).atZone(ZoneId.systemDefault()).toInstant()); // 토큰은 2분만 유지되도록 설정, 2분 후 refresh token
        JwtAuthToken accessToken = jwtAuthTokenProvider.createAuthToken(id, Role.USER.getCode(), expiredDate);  //토큰 발급
        return accessToken.getToken();
    }

    @Override
    public String createRefreshToken(String id) {
        Date expiredDate = Date.from(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant()); // refresh토큰은 유효기간이 1년
        JwtAuthToken refreshToken = jwtAuthTokenProvider.createAuthToken(id, Role.USER.getCode(), expiredDate);  //토큰 발급
        return refreshToken.getToken();
    }
}
