package com.mbtimatching.backend.repository;

import com.mbtimatching.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
    User findByUserIdAndPassword(String userId, String password);
}
