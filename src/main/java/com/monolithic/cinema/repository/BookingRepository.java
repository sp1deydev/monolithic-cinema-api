package com.monolithic.cinema.repository;

import com.monolithic.cinema.dto.Response.BookingResponse;
import com.monolithic.cinema.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
        @Query("SELECT b FROM Booking b WHERE b.user.id = :userId AND (:status is NULL OR b.status = :status)")
        List<Booking> findBookingByUserAndStatus(@Param("userId") String userId, @Param("status") String status);

        List<Booking> findByStatusAndCreatedAtBefore(String status, Instant time);

}
