package com.mbtimatching.backend.entity;

import com.mbtimatching.backend.core.type.MbtiType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Table(name = "member")
@Entity
@Getter
@NoArgsConstructor
public class Member {
    //사용자 엔티티
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="gender", nullable = false)
    private String gender;

    @Column(name="mbti", nullable = false, columnDefinition="enum('INTJ','INTP','ENTJ','ENTP','INFJ','INFP','ENFJ','ENFP','ISTJ','ISFJ','ESTJ','ESFJ','ISTP','ISFP','ESTP','ESFP')")
    private MbtiType mbti;

    @Column(name= "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name="nickname", nullable = false)
    private String nickname;

    @Column(name="salt")
    private String salt;

    @Column(name="refresh_Token")
    private String refreshToken;



    @Builder
    public Member(String email, String password, String salt, String gender, MbtiType mbti, String nickname){
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.gender = gender;
        this.mbti = mbti;
        this.nickname = nickname;
    }
    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
}
