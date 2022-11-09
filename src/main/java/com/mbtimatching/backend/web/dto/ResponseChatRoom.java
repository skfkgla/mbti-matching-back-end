package com.mbtimatching.backend.web.dto;

import com.mbtimatching.backend.entity.ChatRoom;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class ResponseChatRoom {
    @Data
    @Builder
    public static class Room {
        private Long roomId;
        private List<String> userNicknames;
    }
}
