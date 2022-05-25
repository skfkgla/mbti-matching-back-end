package com.mbtimatching.backend.repository;

import com.mbtimatching.backend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<Room, Long>{

}
