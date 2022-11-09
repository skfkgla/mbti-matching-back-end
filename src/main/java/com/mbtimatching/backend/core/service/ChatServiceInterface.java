package com.mbtimatching.backend.core.service;

import com.mbtimatching.backend.entity.ChatRoom;

import java.util.List;

public interface ChatServiceInterface {
    List<ChatRoom> findAllChatRoom();
    void createChatRoom(String email, String name);
    List<ChatRoom> findByMemberChatRooms(String email);
}
