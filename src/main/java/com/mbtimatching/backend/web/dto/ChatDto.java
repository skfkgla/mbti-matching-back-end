package com.mbtimatching.backend.web.dto;

import com.mbtimatching.backend.core.type.ChatStatus;
import lombok.*;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Getter
public class ChatDto {
    @Data
    @Builder
    public static class MessageDto {
        private String nickname;
        private String context;
        private String roomId;
        //private LocalDateTime date = LocalDateTime.now();
    }
    @Data
    @Builder
    public static class ChatRoomDto{
        private String roomId;
        private String email;
        private Set<WebSocketSession> sessions = new HashSet<>();
    }
}
