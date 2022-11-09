package com.mbtimatching.backend.web;

import com.mbtimatching.backend.entity.ChatRoom;
import com.mbtimatching.backend.provider.security.JwtAuthToken;
import com.mbtimatching.backend.provider.security.JwtAuthTokenProvider;
import com.mbtimatching.backend.provider.service.ChatService;
import com.mbtimatching.backend.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatService chatService;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    /**
     * 채팅방 생성
     */
    //TODO 채팅방 이름 어떻게 설정할지 정해야함
    @PostMapping("/chat/room")
    public ResponseEntity<CommonResponse> createRoom(HttpServletRequest request, String name) {
        Optional<String> token = jwtAuthTokenProvider.resolveToken(request);
        String email = null;
        if(token.isPresent()){
            JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(token.get());
            email = jwtAuthToken.getData().getSubject();
        }

        chatService.createChatRoom(email, name);

        CommonResponse response = CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("채팅방 등록 성공")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 채팅방 리스트 보기
     * @return
     */
    @GetMapping("/chat/rooms")
    public ResponseEntity<CommonResponse> roomList(HttpServletRequest request) {
        Optional<String> token = jwtAuthTokenProvider.resolveToken(request);
        String email = null;
        if(token.isPresent()){
            JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(token.get());
            email = jwtAuthToken.getData().getSubject();
        }
        List<ChatRoom> roomList = chatService.findAllChatRoom();

        CommonResponse response = CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("채팅방 조회 성공")
                .list(roomList)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/chat/rooms/")
    public ResponseEntity<CommonResponse> getUserChatRooms(HttpServletRequest request) {
        List<ChatRoom> roomList = chatService.findAllChatRoom();

        CommonResponse response = CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("채팅방 조회 성공")
                .list(roomList)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
