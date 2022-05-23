package com.mbtimatching.backend.provider.service;

import com.mbtimatching.backend.core.type.MbtiType;
import com.mbtimatching.backend.entity.User;
import com.mbtimatching.backend.repository.UserRepository;
import com.mbtimatching.backend.web.dto.RequestMatching;
import com.mbtimatching.backend.web.dto.RequestUser;
import com.mbtimatching.backend.web.dto.ResponseMatching;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
    void randomMatchingTest() {

        RequestUser.Register user = RequestUser.Register.builder()
                .userId("test")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ISTJ)
                .birth(new Date())
                .nickname("닉네임")
                .build();
        userService.register(user);
        RequestUser.Register user1 = RequestUser.Register.builder()
                .userId("test1")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ESFJ)
                .birth(new Date())
                .nickname("닉네임")
                .build();
        userService.register(user1);
        RequestUser.Register user2 = RequestUser.Register.builder()
                .userId("test2")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ESFP)
                .birth(new Date())
                .nickname("닉네임")
                .build();
        userService.register(user2);
        RequestUser.Register user3 = RequestUser.Register.builder()
                .userId("test3")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ESFP)
                .birth(new Date())
                .nickname("닉네임")
                .build();
        userService.register(user3);

        ResponseMatching.Select matching = matchingService.randomMatching(user.getUserId()).orElseGet(() -> null);
        System.out.println(matching.getMatchingUser().getUserId()+"-------------------");
        ResponseMatching.Select matching1 = matchingService.randomMatching(user.getUserId()).orElseGet(() -> null);
        System.out.println(matching1.getMatchingUser().getUserId()+"-------------------");
        ResponseMatching.Select matching2 = matchingService.randomMatching(user.getUserId()).orElseGet(() -> null);
        System.out.println(matching2.getMatchingUser().getUserId()+"-------------------");
        assertNotNull(matchingService.randomMatching(user.getUserId()));
    }

    @Test
    @DisplayName("mbti선택 매칭 서비스 테스트")
    @Transactional
    void selectMatchingTest() {

        RequestUser.Register user = RequestUser.Register.builder()
                .userId("test")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ISTJ)
                .birth(new Date())
                .nickname("닉네임")
                .build();
        userService.register(user);
        RequestUser.Register user1 = RequestUser.Register.builder()
                .userId("test1")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ISTJ)
                .birth(new Date())
                .nickname("닉네임")
                .build();
        userService.register(user1);
        RequestUser.Register user2 = RequestUser.Register.builder()
                .userId("test2")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ESTJ)
                .birth(new Date())
                .nickname("닉네임")
                .build();
        userService.register(user2);
        RequestUser.Register user3 = RequestUser.Register.builder()
                .userId("test3")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ESTJ)
                .birth(new Date())
                .nickname("닉네임")
                .build();
        userService.register(user3);

        ResponseMatching.Select matching = matchingService.selectMatching(user.getUserId(), MbtiType.ESTJ).orElseGet(() -> null);
        System.out.println(matching.getMatchingUser().getUserId());
        assertNotNull(matchingService.randomMatching(user.getUserId()));
    }
}
