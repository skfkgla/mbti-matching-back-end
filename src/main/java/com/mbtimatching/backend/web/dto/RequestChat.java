package com.mbtimatching.backend.web.dto;

import lombok.*;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Getter
public class RequestChat {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ChatMessage {
        private Long roomId;
        private String sender;
        private String message;
        private LocalDateTime sendDate;

    }
}
