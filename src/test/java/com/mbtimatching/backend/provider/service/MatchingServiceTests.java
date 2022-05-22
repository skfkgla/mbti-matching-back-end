package com.mbtimatching.backend.provider.service;

import com.mbtimatching.backend.core.type.MbtiType;
import com.mbtimatching.backend.repository.UserRepository;
import com.mbtimatching.backend.web.dto.RequestUser;
import com.mbtimatching.backend.web.dto.ResponseMatching;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("local")
public class MatchingServiceTests {
    @Autowired
    MatchingService matchingService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("mbti랜덤 매칭 서비스 테스트")
    @Transactional
    void randomMatchingTest(){

        RequestUser.Register user = RequestUser.Register.builder()
                .userId("test")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ENFJ)
                .birth(new Date())
                .nickname("닉네임")
                .build();
        userService.register(user);

        ResponseMatching.Random matching = matchingService.randomMatching(user.getUserId()).orElseGet(()->null);
        System.out.println(matching);
        assertNotNull(matchingService.randomMatching(user.getUserId()));
    }
}
