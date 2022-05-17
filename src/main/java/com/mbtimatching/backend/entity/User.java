package com.mbtimatching.backend.entity;

import com.mbtimatching.backend.core.type.MbtiType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "user")
@Entity
@Getter
@NoArgsConstructor
public class User {
    //사용자 엔티티
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="user_id")
    private String userId;

    @Column(name="password")
    private String password;

    @Column(name="gender")
    private String gender;

    @Column(name="mbti")
    private MbtiType mbti;

    @Column(name="birth")
    private Date birth;

    @Column(name="nickname")
    private String nickname;

    @Column(name="salt")
    private String salt;

    @Column(name="refreshToken")
    private String refreshToken;

    @Builder
    public User(String userId, String password, String salt, String gender, MbtiType mbti, Date birth, String nickname){
        this.userId = userId;
        this.password = password;
        this.salt = salt;
        this.birth = birth;
        this.gender = gender;
        this.mbti = mbti;
        this.nickname = nickname;
    }
}
