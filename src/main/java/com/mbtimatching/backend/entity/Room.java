package com.mbtimatching.backend.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "room")
@Entity
@Getter
@NoArgsConstructor
public class Room {
    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;
    @Column(name = "room_name")
    private String roomName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @Builder
    public Room(String roomName,User user) {
        this.roomName = roomName;
        this.user = user;
    }



}
