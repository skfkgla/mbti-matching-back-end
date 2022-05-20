package com.mbtimatching.backend.provider.service;

import com.mbtimatching.backend.core.security.role.Role;
import com.mbtimatching.backend.core.service.UserServiceInterface;
import com.mbtimatching.backend.entity.User;
import com.mbtimatching.backend.exception.error.LoginFailedException;
import com.mbtimatching.backend.exception.error.RegisterFailedException;
import com.mbtimatching.backend.provider.security.JwtAuthToken;
import com.mbtimatching.backend.provider.security.JwtAuthTokenProvider;
import com.mbtimatching.backend.repository.UserRepository;
import com.mbtimatching.backend.util.SHA256Util;
import com.mbtimatching.backend.web.dto.RequestUser;
import com.mbtimatching.backend.web.dto.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import javax.xml.ws.Response;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

        @Transactional
        @Override
        public void register(RequestUser.Register registerDto) {
            User user = userRepository.findByUserId(registerDto.getUserId());
            if(user != null) {
                // 이미 존재하는 아이디
                throw new RegisterFailedException();
            }
            //salt 생성
            String salt = SHA256Util.generateSalt();
            //sha256으로 솔트와 함께 암호화
            String encryptedPassword = SHA256Util.getEncrypt(registerDto.getPassword(), salt);
            //db에 기입
            user = User.builder()
                    .userId(registerDto.getUserId())
                    .password(encryptedPassword)
                    .salt(salt)
                    .gender(registerDto.getGender())
                    .birth(registerDto.getBirth())
                    .mbti(registerDto.getMbti())
                    .nickname(registerDto.getNickname())
                    .build();
            userRepository.save(user);
        }
        @Transactional
        @Override
        public Optional<ResponseUser.Login> login(RequestUser.Login loginDto){
            //아이디로 유저 꺼내기
            User user = userRepository.findByUserId(loginDto.getUserId());
            if(user == null){   //없으면 예외
                throw new LoginFailedException();
            }
            //salt 꺼내기
            String salt =user.getSalt();
            //salt와 loginDto를 조합해서 암호화
            String encryptedPassword =SHA256Util.getEncrypt(loginDto.getPassword(), salt);
            //비교
            user = userRepository.findByUserIdAndPassword(loginDto.getUserId(), encryptedPassword);
            ResponseUser.Login login = null;
            if(user != null){
                //로그인 성공
                String refreshToken = createRefreshToken(user.getUserId());
                login = ResponseUser.Login.builder()
                        .accessToken(createAccessToken(user.getUserId()))
                        .refreshToken(refreshToken)
                        .build();
                user.updateRefreshToken(refreshToken);  //로그인 할 때 마다 리프레시 토큰 업데이트
            }else {
                throw new LoginFailedException();
            }
            return Optional.ofNullable(login);
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
