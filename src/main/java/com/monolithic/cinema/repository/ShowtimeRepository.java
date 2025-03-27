package com.monolithic.cinema.repository;

import com.monolithic.cinema.dto.Response.ShowtimeResponse;
import com.monolithic.cinema.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, String> {


    @Query("SELECT new com.monolithic.cinema.dto.Response.ShowtimeResponse( " +
            "s.id, s.showtime, r.name, c.name, s.movie.title) " +
            "FROM Showtime s " +
            "JOIN s.room r " +
            "JOIN r.cinema c " +
            "WHERE(?1 is NULL OR c.id = ?1)"+
            "AND(?2 is NULL OR s.movie.id = ?2)")
    List<ShowtimeResponse> findShowtimeByCinemaIdAndMovieId(@Param("cinemaId") String cinemaId, @Param("movieId") String movieId);


    @Query("SELECT COUNT(s) " +
            "FROM Showtime s " +
            "JOIN s.room r " +
            "JOIN r.cinema c " +
            "WHERE(?1 is NULL OR c.id = ?1)"+
            "AND(?2 is NULL OR s.movie.id = ?2)")

    Integer countShowtimes(@Param("cinemaId") String cinemaId, @Param("movieId") String movieId);
}
