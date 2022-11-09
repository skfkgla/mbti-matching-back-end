package com.mbtimatching.backend.provider.service;


import com.mbtimatching.backend.core.type.MbtiType;
import com.mbtimatching.backend.repository.MemberRepository;
import com.mbtimatching.backend.web.dto.RequestUser;
import com.mbtimatching.backend.web.dto.ResponseUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class MemberServiceTests {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입 서비스 테스트")
    @Transactional
    void registerTest(){

        RequestUser.Register member = RequestUser.Register.builder()
                .email("test")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ENFJ)
                .nickname("닉네임")
                .build();
        memberService.register(member);

        assertNotNull(memberRepository.findByEmail("test"));
    }
    @Test
    @DisplayName("로그인 서비스 테스트")
    @Transactional
    void loginTest(){
        //회원 정보 등록
        RequestUser.Register member = RequestUser.Register.builder()
                .email("test")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ENFJ)
                .nickname("닉네임")
                .build();
        memberService.register(member);
        //로그인
        RequestUser.Login loginDto = RequestUser.Login.builder()
                .email("test")
                .password("test")
                .build();
        //token이 잘 발급 되는지 확인
        ResponseUser.Login loginResponse = memberService.login(loginDto).orElseGet(()-> null);
        System.out.println(loginResponse.getAccessToken());
        System.out.println(loginResponse.getRefreshToken());
        assertNotNull(loginResponse);
    }
    @Test
    @DisplayName("리프레시 토큰 서비스 테스트")
    @Transactional
    void refreshTokenTest(){
        //회원 정보 등록
        RequestUser.Register member = RequestUser.Register.builder()
                .email("test")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ENFJ)
                .nickname("닉네임")
                .build();
        memberService.register(member);
        //로그인
        RequestUser.Login loginDto = RequestUser.Login.builder()
                .email("test")
                .password("test")
                .build();
        //token이 잘 발급 되는지 확인
        ResponseUser.Login loginResponse = memberService.login(loginDto).orElseGet(()-> null);

        ResponseUser.Token newToken = memberService.refreshToken(loginResponse.getRefreshToken()).orElseGet(()->null);
        assertNotNull(newToken.getRefreshToken());
        assertNotNull(newToken.getAccessToken());
        System.out.println(newToken.getRefreshToken());
        System.out.println(newToken.getAccessToken());
    }


}
