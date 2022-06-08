package com.mbtimatching.backend.web.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class RequestChatRoom {
    @Data
    @Builder
    public static class Room{
        private String name;
    }

}
