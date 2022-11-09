package com.mbtimatching.backend.provider.service;

import com.mbtimatching.backend.core.service.MatchingServiceInterface;
import com.mbtimatching.backend.core.type.MatchingType;
import com.mbtimatching.backend.core.type.MbtiType;
import com.mbtimatching.backend.entity.Member;
import com.mbtimatching.backend.exception.error.CustomJwtRuntimeException;
import com.mbtimatching.backend.provider.security.JwtAuthTokenProvider;
import com.mbtimatching.backend.repository.MemberRepository;
import com.mbtimatching.backend.web.dto.ResponseMatching;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchingService implements MatchingServiceInterface {
    private final MemberRepository memberRepository;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    @Transactional
    @Override
    public ResponseMatching.Select randomMatching(String email){ //
        Member member = memberRepository.findByEmail(email);
        if(member == null){
            throw new CustomJwtRuntimeException();
        }
        //해당 유저 mbti 받아오기
        String mbti = member.getMbti().toString();
        MatchingType matchingType = null;
        //mbti가 존재한다면 가져오고 아니면 예외 처리
        try {
            matchingType = MatchingType.valueOf(mbti);
        }
        catch (IllegalArgumentException e){
            System.out.println("해당 유저 mbti를 찾지 못함");
            throw new IllegalArgumentException(e);
        }
        List<Member> members = null;
        //두개의 가장 잘 맞는 mbti 타입중에 하나 고르기
        int random = (int)(Math.random()*2);
        if(random == 1){
            members = memberRepository.findBySelcetMbti(matchingType.getFirst());
        }else{
            members = memberRepository.findBySelcetMbti(matchingType.getSecond());
        }
        //해당 mbti타입중에 랜덤하게 유저 한명 고르기
        random = (int)(Math.random()* members.size());
        Member matchingMember = members.get(random);
        ResponseMatching.Select matching = ResponseMatching.Select.builder()
                .nickname(matchingMember.getNickname())
                .mbti(matchingMember.getMbti().toString())
                .build();
        return matching;
    }
    @Transactional
    @Override
    public Optional<ResponseMatching.Select> selectMatching(String email, MbtiType mbti){ //
        Member member = memberRepository.findByEmail(email);
        if(member == null){
            System.out.println("유저를 찾지 못함 selectMatching service부분");
            throw new CustomJwtRuntimeException();
        }
        List<Member> members = memberRepository.findBySelcetMbti(mbti);
        int random=(int)(Math.random()*(members.size()));
        Member matchingMember = members.get(random);
        ResponseMatching.Select matching = ResponseMatching.Select.builder()
                .nickname(matchingMember.getNickname())
                .mbti(matchingMember.getMbti().toString())
                .build();
        return Optional.ofNullable(matching);
    }

}
