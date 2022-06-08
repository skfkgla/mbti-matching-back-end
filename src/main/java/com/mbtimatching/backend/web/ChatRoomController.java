package com.mbtimatching.backend.web;

import com.mbtimatching.backend.entity.Chat;
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
     * 채팅방 참여하기
     * @param roomId 채팅방 id
     */
    @GetMapping("/{roomId}")
    public String joinRoom(@PathVariable(required = false) String roomId) {

        return "chat/room";
    }

    /**
     * 채팅방 등록
     */
    @PostMapping("/room")
    public ResponseEntity<CommonResponse> createRoom(HttpServletRequest request, @RequestBody String name) {
        Optional<String> token = jwtAuthTokenProvider.resolveToken(request);
        String userId = null;
        if(token.isPresent()){
            JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(token.get());
            userId = jwtAuthToken.getData().getSubject();
        }
        ResponseChatRoom.Room room = chatService.createRoom(userId,name).orElseGet(()->null);
        CommonResponse response = CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("채팅방 등록 성공")
                .list(room)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 채팅방 리스트 보기
     * @테스트 및 발표 끝나면 수정할 것
     * @return
     */
    @GetMapping("/roomList")
    public ResponseEntity<CommonResponse> roomList(HttpServletRequest request, Model model) {
        Optional<String> token = jwtAuthTokenProvider.resolveToken(request);
        String userId = null;
        if(token.isPresent()){
            JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(token.get());
            userId = jwtAuthToken.getData().getSubject();
        }
        ResponseChatRoom.RoomList roomList = chatService.findChatRoomList(userId).orElseGet(null);

        CommonResponse response = CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("채팅방 조회 성공")
                .list(roomList)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 방만들기 폼
     */
    @GetMapping("/roomForm")
    public String roomForm() {
        return "chat/roomForm";
    }

}
