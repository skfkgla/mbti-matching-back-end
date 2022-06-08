package com.mbtimatching.backend.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@RequiredArgsConstructor
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //클라이언트로 메세지를 응답 해 줄 때 prefix정의 - 서버 -> 클라
        registry.enableSimpleBroker("/room");
        //클라이언트에서 메세지 송신 시 prefix정의 - 클라 -> 서버
        registry.setApplicationDestinationPrefixes("/send");
    }
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //the url is for Websocket handshake
        registry.addEndpoint("/ws-stomp") //handshake가 될 endpoint지정
                .setAllowedOrigins("*")
                .withSockJS(); //SockJS사용
    }

}