package com.mbtimatching.backend.web.dto;

import com.mbtimatching.backend.entity.Member;
import lombok.Builder;
import lombok.Data;

public class ResponseMatching {
    @Builder
    @Data
    public static class Select {
        private String nickname;
        private String mbti;
    }

}
