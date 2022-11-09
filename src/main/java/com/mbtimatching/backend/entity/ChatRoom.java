package com.mbtimatching.backend.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ChatRoom {
    private String uuid;
    private String name;

    private List<String> nicknames;
    private List<String> sessions;

    public ChatRoom(String uuid, String name){
        this.uuid = uuid;
        this.name = name;

        this.nicknames = new ArrayList<>();
        this.sessions = new ArrayList<>();
    }
    public void registerSession(String session) {
        this.sessions.add(session);
    }
    public void unregisterSession(String session) {
        this.sessions.remove(session);
    }
    public void registerNickname(String nickname) {
        this.nicknames.add(nickname);
    }
    public void unregisterNickname(String nickname) {
        this.nicknames.remove(nickname);
    }
}
