package com.mbtimatching.backend.web.dto;

import lombok.Builder;
import lombok.Data;

public class RequestChatRoom {
    @Builder
    @Data
    public static class RoomForm{
        private String name;
    }
}
