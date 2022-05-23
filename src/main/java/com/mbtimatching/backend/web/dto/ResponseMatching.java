package com.mbtimatching.backend.web.dto;

import com.mbtimatching.backend.core.type.MbtiType;
import com.mbtimatching.backend.entity.User;
import lombok.Builder;
import lombok.Data;

public class ResponseMatching {
    @Builder
    @Data
    public static class Select {
        private User matchingUser;
    }

}
