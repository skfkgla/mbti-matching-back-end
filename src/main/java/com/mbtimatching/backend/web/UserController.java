package com.mbtimatching.backend.web;

import com.mbtimatching.backend.provider.service.UserService;
import com.mbtimatching.backend.web.dto.CommonResponse;
import com.mbtimatching.backend.web.dto.RequestUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<CommonResponse> requestRegister(@Valid @RequestBody RequestUser.Register registerDto){
        userService.register(registerDto);

        CommonResponse response =CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("회원 등록 성공")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
