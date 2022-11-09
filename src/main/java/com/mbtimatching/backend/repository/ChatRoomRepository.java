package com.mbtimatching.backend.repository;

import com.mbtimatching.backend.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ChatRoomRepository {
    private List<ChatRoom> chatRooms;

    @PostConstruct
    private void init() {
        this.chatRooms = new ArrayList<>();
    }

    @PreDestroy
    private void destroy() {
        this.chatRooms.clear();
        System.out.println("채팅방 리스트 객체 소멸 직전");
    }

    public ChatRoom createChatRoom(String name) {
        ChatRoom chatRoom = new ChatRoom(UUID.randomUUID().toString(), name);
        this.chatRooms.add(chatRoom);

        return chatRoom;
    }

    public void registerToRoom(String uuid, String session) {
        for (int i = 0; i < chatRooms.size(); i++) {
            if (chatRooms.get(i).getUuid().equals(uuid)) {
                chatRooms.get(i).registerSession(session);
                break;
            }
        }
    }

    public void unregisterFromRoom(String uuid, String session) {
        for (int i = 0; i < chatRooms.size(); i++) {
            if (chatRooms.get(i).getUuid().equals(uuid)) {
                chatRooms.get(i).unregisterSession(session);
                break;
            }
        }
    }

    public List<ChatRoom> getAllChatRoom() {
        return this.chatRooms;
    }

    public List<ChatRoom> getChatRoomsByNickname(String nickname) {
        List<ChatRoom> chatRoomList = new ArrayList<>();
        for (int i = 0; i < chatRooms.size(); i++) {
            for(int j=0; j < chatRooms.get(i).getNicknames().size(); j++){
                if (chatRooms.get(i).getNicknames().get(j).equals(nickname)) {
                    chatRoomList.add(chatRooms.get(i));
                }
            }
        }
        return chatRoomList;
    }

}
