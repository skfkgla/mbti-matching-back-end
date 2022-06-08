package com.mbtimatching.backend.repository;

import com.mbtimatching.backend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<Room, Long>{
    @Query("select r from Room r " +
            "join r.user u " +
            "where u.userId = :userId")
    List<Room> findAllChatRoom(String userId);
    Room findByRoomId(String roomId);

}
