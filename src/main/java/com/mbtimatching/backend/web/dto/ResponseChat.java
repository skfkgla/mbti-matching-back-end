package com.mbtimatching.backend.web.dto;

import com.mbtimatching.backend.entity.User;
import lombok.Builder;
import lombok.Data;

public class ResponseChat {
    @Builder
    @Data
    public static class Chat {
        private String roomId;
        private String sender;
        private String message;
    }

}
