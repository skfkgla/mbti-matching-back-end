package com.mbtimatching.backend.web.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

public class RequestUser {
        @Builder
        @Data
        public static class Register {
            @NotEmpty(message = "이메일이 비어있음")
            private String userId;
            @NotEmpty(message = "비밀번호 입력이 되어있지 않음")
            private String password;
        }
}
