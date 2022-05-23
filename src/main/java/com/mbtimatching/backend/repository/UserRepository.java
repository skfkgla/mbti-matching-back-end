package com.mbtimatching.backend.repository;

import com.mbtimatching.backend.core.type.MbtiType;
import com.mbtimatching.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
    User findByUserIdAndPassword(String userId, String password);
    User findByRefreshToken(String refreshToken);
    @Query("select u from User u " +
            "where u.mbti = :mbti ")
    List<User> findBySelcetMbti(MbtiType mbti);
}
