package com.mbtimatching.backend.provider.service;

import com.mbtimatching.backend.core.service.ChatServiceInterface;
import com.mbtimatching.backend.entity.ChatRoom;
import com.mbtimatching.backend.entity.Member;
import com.mbtimatching.backend.exception.error.CustomJwtRuntimeException;
import com.mbtimatching.backend.repository.ChatRoomRepository;
import com.mbtimatching.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService implements ChatServiceInterface {
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;

    /**
     * 모든 채팅방 찾기
     */
    @Override
    @Transactional
    public List<ChatRoom> findAllChatRoom() {
        return chatRoomRepository.getAllChatRoom();
    }

    /**
     * 채팅방 만들기
     * @param email 만든사람 이름
     * @param name 방 이름
     */
    @Override
    @Transactional
    public void createChatRoom(String email, String name) {
        Member member = memberRepository.findByEmail(email);
        if(member == null){
            throw new CustomJwtRuntimeException();
        }

       ChatRoom chatRoom = chatRoomRepository.createChatRoom(name + " - " + member.getNickname());
       chatRoom.registerNickname(member.getNickname());
       chatRoom.registerNickname(name);
    }

    /**
     * 멤버의 채팅방 리스트 조회
     * @param email 방 이름
     */
    @Override
    @Transactional
    public List<ChatRoom> findByMemberChatRooms(String email) {
        Member member = memberRepository.findByEmail(email);
        if(member == null){
            throw new CustomJwtRuntimeException();
        }
        return chatRoomRepository.getChatRoomsByNickname(member.getNickname());
    }

}
