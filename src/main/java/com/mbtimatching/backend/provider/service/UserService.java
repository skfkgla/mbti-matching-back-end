package com.mbtimatching.backend.provider.service;

import com.mbtimatching.backend.core.service.UserServiceInterface;
import com.mbtimatching.backend.entity.User;
import com.mbtimatching.backend.provider.security.JwtAuthTokenProvider;
import com.mbtimatching.backend.repository.UserRepository;
import com.mbtimatching.backend.util.SHA256Util;
import com.mbtimatching.backend.web.dto.RequestUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                //throw new RegisterFailedException();
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

}
