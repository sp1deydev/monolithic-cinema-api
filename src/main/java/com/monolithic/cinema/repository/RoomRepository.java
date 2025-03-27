package com.monolithic.cinema.repository;

import com.monolithic.cinema.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    @Modifying
    @Query("DELETE FROM Seat s WHERE s.room.id = :roomId")
    void deleteSeatsByRoomId(@Param("roomId") String roomId);

    @Modifying
    @Query("DELETE FROM Room r WHERE r.id = :roomId")
    void deleteRoomById(@Param("roomId") String roomId);


}
