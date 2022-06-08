package com.mbtimatching.backend.provider.service;

import com.mbtimatching.backend.entity.Chat;
import com.mbtimatching.backend.entity.Room;
import com.mbtimatching.backend.entity.User;
import com.mbtimatching.backend.repository.ChatRepository;
import com.mbtimatching.backend.repository.ChatRoomRepository;
import com.mbtimatching.backend.repository.UserRepository;
import com.mbtimatching.backend.web.dto.ResponseChat;
import com.mbtimatching.backend.web.dto.ResponseChatRoom;
import com.mbtimatching.backend.web.dto.ResponseMatching;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    /**
     * 모든 채팅방 찾기
     */
    public Optional<ResponseChatRoom.RoomList> findChatRoomList(String userId) {
        List<Long> roomList =new ArrayList<>();
        List<Room> rooms = chatRoomRepository.findAllChatRoom(userId);
        ResponseChatRoom.RoomList.RoomListBuilder itemBuilder = ResponseChatRoom.RoomList.builder();
        for(int i = 0 ; i < rooms.size() ; i++) {
            Room room =rooms.get(i);
            roomList.add(room.getRoomId());
        }
        ResponseChatRoom.RoomList resRoomList = ResponseChatRoom.RoomList.builder()
                .roomList(roomList)
                .build();

        return Optional.ofNullable(resRoomList);
    }


    /**
     * 채팅방 만들기
     * @param name 방 이름
     */
    public Optional<ResponseChatRoom.Room> createRoom(String userId,String name) {
        Room room = Room.builder()
                .roomName(name)
                .user(userRepository.findByUserId(userId))
                .build();
        chatRoomRepository.save(room);
        ResponseChatRoom.Room resRoom = ResponseChatRoom.Room.builder()
                .roomId((room.getRoomId()).toString())
                .build();
        User user = userRepository.findByUserId(userId);
        user.addRoom(room);

        return Optional.ofNullable(resRoom);
    }

    /////////////////

    /**
     * 채팅 생성
     * @param roomId 채팅방 id
     * @param sender 보낸이
     * @param message 내용
     */
    public Chat createChat(String roomId, String sender, String message) {
        Room room = chatRoomRepository.findByRoomId(roomId); //방 찾기
        return chatRepository.save(Chat.createChat(room, sender, message));
    }

    /**
     * 채팅방 채팅내용 불러오기
     * @param roomId 채팅방 id
     */
//    public List<Chat> findAllChatByRoomId(Long roomId) {
//        return chatRepository.findAllByRoomId(roomId);
//    }


}
