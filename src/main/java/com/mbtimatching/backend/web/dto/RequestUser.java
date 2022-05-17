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
            private String userId;
            @NotEmpty(message = "비밀번호 입력이 되어있지 않음")
            private String password;
            @NotEmpty(message = "성별이 입력되지 않음")
            private String gender;
            @NotEmpty(message = "MBTI가 입력되지 않음")
            private MbtiType mbti;
            @NotEmpty(message = "생년월일이 입력되지 않음")
            private Date birth;
            @NotEmpty(message = "닉네임이 입력되지 않음")
            private String nickname;
        }
}
