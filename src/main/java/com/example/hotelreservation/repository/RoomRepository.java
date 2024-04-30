package com.example.hotelreservation.repository;

import com.example.hotelreservation.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r WHERE r.roomNumber = :roomNumber")
    Optional<Room> findByRoomNumber(Long roomNumber);
}
