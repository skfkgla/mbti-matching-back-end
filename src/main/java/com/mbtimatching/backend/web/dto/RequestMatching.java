package com.mbtimatching.backend.web.dto;

import com.mbtimatching.backend.core.type.MbtiType;
import lombok.Builder;
import lombok.Data;

public class RequestMatching {
    @Builder
    @Data
    public static class Matching{
        private MbtiType mbti;
    }

}
