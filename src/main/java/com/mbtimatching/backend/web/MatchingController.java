package com.mbtimatching.backend.web;

import com.mbtimatching.backend.core.security.role.Role;
import com.mbtimatching.backend.core.type.MbtiType;
import com.mbtimatching.backend.provider.security.JwtAuthToken;
import com.mbtimatching.backend.provider.security.JwtAuthTokenProvider;
import com.mbtimatching.backend.provider.service.MatchingService;
import com.mbtimatching.backend.web.dto.CommonResponse;
import com.mbtimatching.backend.web.dto.RequestMatching;
import com.mbtimatching.backend.web.dto.ResponseMatching;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MatchingController {
    private final MatchingService matchingService;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    @GetMapping("/matching/random")      //프론트에서는 해당 유저의 mbti를 넣어서 반환
    public ResponseEntity<CommonResponse> randomMatching(HttpServletRequest request){
        Optional<String> token = jwtAuthTokenProvider.resolveToken(request);
        String userId = null;
        if(token.isPresent()){
            JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(token.get());
            userId = jwtAuthToken.getData().getSubject();
        }
        ResponseMatching.Select user = matchingService.randomMatching(userId);
        CommonResponse response = CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("랜덤 매칭 성공")
                .list(user)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/matching/select")
    public ResponseEntity<CommonResponse> selcetMatching(HttpServletRequest request, @RequestParam MbtiType mbti){
        Optional<String> token = jwtAuthTokenProvider.resolveToken(request);
        String userId = null;
        if(token.isPresent()){
            JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(token.get());
            userId = jwtAuthToken.getData().getSubject();
        }
        ResponseMatching.Select user = matchingService.selectMatching(userId, mbti).orElseGet(()-> null);
        CommonResponse response = CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("선택 매칭 성공")
                .list(user)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
