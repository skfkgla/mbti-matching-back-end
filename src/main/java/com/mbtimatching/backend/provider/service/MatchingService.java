package com.mbtimatching.backend.provider.service;

import com.mbtimatching.backend.core.security.role.Role;
import com.mbtimatching.backend.core.service.MatchingServiceInterface;
import com.mbtimatching.backend.core.type.MatchingType;
import com.mbtimatching.backend.core.type.MbtiType;
import com.mbtimatching.backend.entity.User;
import com.mbtimatching.backend.exception.error.CustomJwtRuntimeException;
import com.mbtimatching.backend.provider.security.JwtAuthToken;
import com.mbtimatching.backend.provider.security.JwtAuthTokenProvider;
import com.mbtimatching.backend.repository.UserRepository;
import com.mbtimatching.backend.web.dto.RequestMatching;
import com.mbtimatching.backend.web.dto.ResponseMatching;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchingService implements MatchingServiceInterface {
    private final UserRepository userRepository;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    @Transactional
    @Override
    public Optional<ResponseMatching.Select> randomMatching(String userId){ //
        User user = userRepository.findByUserId(userId);
        if(user == null){
            System.out.println("유저를 찾지 못함");
            throw new CustomJwtRuntimeException();
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
        List<User> users = null;
        //두개의 가장 잘 맞는 mbti 타입중에 하나 고르기
        int random = (int)(Math.random()*2);
        if(random == 1){
            users = userRepository.findBySelcetMbti(matchingType.getFirst());
        }else{
            users = userRepository.findBySelcetMbti(matchingType.getSecond());
        }
        //해당 mbti타입중에 랜덤하게 유저 한명 고르기
        random = (int)(Math.random()*users.size());
        User matchingUser= users.get(random);
        ResponseMatching.Select matching = ResponseMatching.Select.builder()
                .matchingUser(matchingUser)
                .build();
        return Optional.ofNullable(matching);
    }
    @Transactional
    @Override
    public Optional<ResponseMatching.Select> selectMatching(String userId, MbtiType mbti){ //
        User user = userRepository.findByUserId(userId);
        if(user == null){
            System.out.println("유저를 찾지 못함");
            throw new CustomJwtRuntimeException();
        }
        List<User> users = userRepository.findBySelcetMbti(mbti);
        int random=(int)(Math.random()*(users.size()));
        User matchingUser= users.get(random);
        ResponseMatching.Select matching = ResponseMatching.Select.builder()
                .matchingUser(matchingUser)
                .build();
        return Optional.ofNullable(matching);
    }

}
