package com.mbtimatching.backend.web.dto;

import com.mbtimatching.backend.core.type.MbtiType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class RequestUser {
        @Builder
        @Data
        public static class Register {
            @NotEmpty(message = "이메일이 비어있음")
            private String email;
            @NotEmpty(message = "비밀번호 입력이 되어있지 않음")
            private String password;
            private String gender;
            private MbtiType mbti;
            private String nickname;
        }

        @Builder
        @Data
        public static class Login{
            private String email;
            private String password;
        }
}
