package com.mbtimatching.backend.web.dto;

import com.mbtimatching.backend.core.type.MbtiType;
import lombok.Builder;
import lombok.Data;

public class ResponseMatching {
    @Builder
    @Data
    public static class Random {
        private MbtiType first;
        private MbtiType second;
    }
}
