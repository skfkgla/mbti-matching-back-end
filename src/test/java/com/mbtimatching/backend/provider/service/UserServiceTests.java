package com.mbtimatching.backend.provider.service;


import com.mbtimatching.backend.core.type.MbtiType;
import com.mbtimatching.backend.entity.User;
import com.mbtimatching.backend.repository.UserRepository;
import com.mbtimatching.backend.web.dto.RequestUser;
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
}
