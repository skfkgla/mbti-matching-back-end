package com.mbtimatching.backend.web.dto;

import com.mbtimatching.backend.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class ResponseChatRoom {
    @Data
    @Builder
    public static class Room {
        private String roomId;
    }
    @Data
    @Builder
    public static class RoomList{
        private List<Long> roomList;
    }
}
