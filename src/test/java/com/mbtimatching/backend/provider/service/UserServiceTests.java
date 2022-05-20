package com.mbtimatching.backend.provider.service;


import com.mbtimatching.backend.core.type.MbtiType;
import com.mbtimatching.backend.entity.User;
import com.mbtimatching.backend.exception.error.LoginFailedException;
import com.mbtimatching.backend.repository.UserRepository;
import com.mbtimatching.backend.web.dto.RequestUser;
import com.mbtimatching.backend.web.dto.ResponseUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("회원가입 서비스 테스트")
    @Transactional
    void registerTest(){

        RequestUser.Register user = RequestUser.Register.builder()
                .userId("test")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ENFJ)
                .birth(new Date())
                .nickname("닉네임")
                .build();
        userService.register(user);

        assertNotNull(userRepository.findByUserId("test"));
    }
    @Test
    @DisplayName("로그인 서비스 테스트")
    @Transactional
    void loginTest(){
        //회원 정보 등록
        RequestUser.Register user = RequestUser.Register.builder()
                .userId("test")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ENFJ)
                .birth(new Date())
                .nickname("닉네임")
                .build();
        userService.register(user);
        //로그인
        RequestUser.Login loginDto = RequestUser.Login.builder()
                .userId("test")
                .password("test")
                .build();
        //token이 잘 발급 되는지 확인
        ResponseUser.Login loginResponse = userService.login(loginDto).orElseGet(()-> null);
        System.out.println(loginResponse.getAccessToken());
        System.out.println(loginResponse.getRefreshToken());
        assertNotNull(loginResponse);
    }
    @Test
    @DisplayName("리프레시 토큰 서비스 테스트")
    @Transactional
    void refreshTokenTest(){
        //회원 정보 등록
        RequestUser.Register user = RequestUser.Register.builder()
                .userId("test")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ENFJ)
                .birth(new Date())
                .nickname("닉네임")
                .build();
        userService.register(user);
        //로그인
        RequestUser.Login loginDto = RequestUser.Login.builder()
                .userId("test")
                .password("test")
                .build();
        //token이 잘 발급 되는지 확인
        ResponseUser.Login loginResponse = userService.login(loginDto).orElseGet(()-> null);

        ResponseUser.Token newToken = userService.refreshToken(loginResponse.getRefreshToken()).orElseGet(()->null);
        assertNotNull(newToken.getRefreshToken());
        assertNotNull(newToken.getAccessToken());
        System.out.println(newToken.getRefreshToken());
        System.out.println(newToken.getAccessToken());
    }


}
