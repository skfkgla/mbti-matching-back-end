package com.mbtimatching.backend.web;

import com.mbtimatching.backend.entity.Chat;
import com.mbtimatching.backend.provider.service.ChatService;
import com.mbtimatching.backend.repository.ChatRoomRepository;
import com.mbtimatching.backend.web.dto.RequestChat;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/{roomId}") //여기로 전송되면 메서드 호출 -> WebSocketConfig prefixes 에서 적용한건 앞에 생략
    @SendTo("/room/{roomId}")   //구독하고 있는 장소로 메시지 전송 (목적지)  -> WebSocketConfig Broker 에서 적용한건 앞에 붙어줘야됨
    public RequestChat.ChatMessage test(@DestinationVariable String roomId, RequestChat.ChatMessage message) {

        //메세지 브로커를 이용해 roomId를 알려줌
        return RequestChat.ChatMessage.builder()
                .roomId(roomId)
                .sender(message.getSender())
                .message(message.getMessage())
                .build();

    }

}
