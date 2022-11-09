package com.mbtimatching.backend.web;

import com.mbtimatching.backend.web.dto.ChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {
    @Autowired
    private final SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/room/{id}")
    public void sendMessage(@DestinationVariable("id") String id, @Payload ChatDto.MessageDto messageDto){
        System.out.println(messageDto+" "+id);
        simpMessagingTemplate.convertAndSend("/sub/client/" + id , messageDto);
    }
}
