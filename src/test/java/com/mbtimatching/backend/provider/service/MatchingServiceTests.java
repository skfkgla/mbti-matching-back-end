package com.mbtimatching.backend.provider.service;

import com.mbtimatching.backend.core.type.MbtiType;
import com.mbtimatching.backend.repository.MemberRepository;
import com.mbtimatching.backend.web.dto.RequestUser;
import com.mbtimatching.backend.web.dto.ResponseMatching;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("local")
public class MatchingServiceTests {
    @Autowired
    MatchingService matchingService;
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("mbti랜덤 매칭 서비스 테스트")
    @Transactional
    void randomMatchingTest() {

        RequestUser.Register member = RequestUser.Register.builder()
                .email("test")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ISTJ)
                .nickname("닉네임")
                .build();
        memberService.register(member);
        RequestUser.Register member1 = RequestUser.Register.builder()
                .email("test1")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ESFJ)
                .nickname("닉네임")
                .build();
        memberService.register(member1);
        RequestUser.Register member2 = RequestUser.Register.builder()
                .email("test2")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ESFP)
                .nickname("닉네임")
                .build();
        memberService.register(member2);
        RequestUser.Register member3 = RequestUser.Register.builder()
                .email("test3")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ESFP)
                .nickname("닉네임")
                .build();
        memberService.register(member3);

        ResponseMatching.Select matching = matchingService.randomMatching(member.getEmail());
        System.out.println(matching.getNickname());
        ResponseMatching.Select matching1 = matchingService.randomMatching(member.getEmail());
        System.out.println(matching1.getNickname()+"-------------------");
        assertNotNull(matchingService.randomMatching(member.getEmail()));
    }

    @Test
    @DisplayName("mbti선택 매칭 서비스 테스트")
    @Transactional
    void selectMatchingTest() {

        RequestUser.Register member = RequestUser.Register.builder()
                .email("test")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ISTJ)
                .nickname("닉네임")
                .build();
        memberService.register(member);
        RequestUser.Register member1 = RequestUser.Register.builder()
                .email("test1")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ISTJ)
                .nickname("닉네임")
                .build();
        memberService.register(member1);
        RequestUser.Register member2 = RequestUser.Register.builder()
                .email("test2")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ESTJ)
                .nickname("닉네임")
                .build();
        memberService.register(member2);
        RequestUser.Register member3 = RequestUser.Register.builder()
                .email("test3")
                .password("test")
                .gender("man")
                .mbti(MbtiType.ESTJ)
                .nickname("닉네임")
                .build();
        memberService.register(member3);

//        ResponseMatching.Select matching = matchingService.selectMatching(member.getUserId(), MbtiType.ESTJ).orElseGet(() -> null);
//        System.out.println(matching.getMatchingUser().getUserId());
//        assertNotNull(matchingService.randomMatching(member.getUserId()));
    }
}
