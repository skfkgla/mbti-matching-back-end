package com.mbtimatching.backend.web;

import com.mbtimatching.backend.exception.error.CustomJwtRuntimeException;
import com.mbtimatching.backend.exception.error.LoginFailedException;
import com.mbtimatching.backend.provider.service.MemberService;
import com.mbtimatching.backend.web.dto.CommonResponse;
import com.mbtimatching.backend.web.dto.RequestUser;
import com.mbtimatching.backend.web.dto.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<CommonResponse> requestRegister(@Valid @RequestBody RequestUser.Register registerDto){
        memberService.register(registerDto);

        CommonResponse response =CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("회원 등록 성공")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/check/{id}")
    public ResponseEntity<CommonResponse> requestRegister(@PathVariable String id){
        memberService.checkEmail(id);

        CommonResponse response =CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("사용할 수 있는 아이디")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<CommonResponse> requestLogin(@RequestBody RequestUser.Login loginDto){
        ResponseUser.Login member = memberService.login(loginDto).orElseThrow(()-> new LoginFailedException());

        CommonResponse response =CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("로그인 성공")
                .list(member)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/refreshToken")
    public ResponseEntity<CommonResponse> refreshToken(@RequestBody Map<String, String> refreshToken){
        System.out.println(refreshToken.get("refreshToken")+" ------------------- ");
        ResponseUser.Token token = memberService.refreshToken(refreshToken.get("refreshToken")).orElseThrow(() -> new CustomJwtRuntimeException());
        CommonResponse response =CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("로그인 성공")
                .list(token)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/home")
    public String getHome(){
        return "Hello World!";
    }

}
