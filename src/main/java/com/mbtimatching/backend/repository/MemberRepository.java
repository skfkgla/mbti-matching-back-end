package com.mbtimatching.backend.repository;

import com.mbtimatching.backend.core.type.MbtiType;
import com.mbtimatching.backend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
    Member findByEmailAndPassword(String userId, String password);
    Member findByRefreshToken(String refreshToken);
    @Query("select m from Member m " +
            "where m.mbti = :mbti ")
    List<Member> findBySelcetMbti(MbtiType mbti);

}
