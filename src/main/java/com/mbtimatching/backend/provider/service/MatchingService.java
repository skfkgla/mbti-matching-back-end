package com.mbtimatching.backend.provider.service;

import com.mbtimatching.backend.core.service.MatchingServiceInterface;
import com.mbtimatching.backend.core.type.MatchingType;
import com.mbtimatching.backend.core.type.MbtiType;
import com.mbtimatching.backend.entity.User;
import com.mbtimatching.backend.repository.UserRepository;
import com.mbtimatching.backend.web.dto.ResponseMatching;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchingService implements MatchingServiceInterface {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public Optional<ResponseMatching.Random> randomMatching(String userId){ //
        User user = userRepository.findByUserId(userId);
        if(user == null){
            System.out.println("유저를 찾지 못함");
        }
        //해당 유저 mbti 받아오기
        String mbti = user.getMbti().toString();
        MatchingType matchingType = null;
        //mbti가 존재한다면 가져오고 아니면 예외 처리
        try {
            matchingType = MatchingType.valueOf(mbti);
        }
        catch (IllegalArgumentException e){
            System.out.println("해당 유저 mbti를 찾지 못함");
            throw new IllegalArgumentException(e);
        }
        ResponseMatching.Random matching = ResponseMatching.Random.builder()
                .first(matchingType.getFirst())
                .second(matchingType.getSecond())
                .build();
        return Optional.ofNullable(matching);
    }
}
