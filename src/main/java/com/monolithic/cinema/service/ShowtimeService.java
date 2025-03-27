package com.monolithic.cinema.service;

import com.monolithic.cinema.dto.Request.ShowtimeRequest;
import com.monolithic.cinema.dto.Response.ShowtimeResponse;
import com.monolithic.cinema.entity.Cinema;
import com.monolithic.cinema.entity.Movie;
import com.monolithic.cinema.entity.Room;
import com.monolithic.cinema.entity.Showtime;
import com.monolithic.cinema.enums.ErrorCode;
import com.monolithic.cinema.exception.CustomException;
import com.monolithic.cinema.mapper.ShowtimeMapper;
import com.monolithic.cinema.repository.MovieRepository;
import com.monolithic.cinema.repository.RoomRepository;
import com.monolithic.cinema.repository.ShowtimeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShowtimeService {
    ShowtimeRepository showtimeRepository;

    ShowtimeMapper showtimeMapper;

    RoomRepository roomRepository;

    MovieRepository movieRepository;

    public ShowtimeResponse createShowtime(ShowtimeRequest showtimeRequest){
        Room room = roomRepository.findById(showtimeRequest.getRoomId())
                .orElseThrow(()-> new CustomException(ErrorCode.RESOURCE_NOT_FOUND,"Room"));

        Movie movie = movieRepository.findById(showtimeRequest.getMovieId())
                .orElseThrow(()-> new CustomException(ErrorCode.RESOURCE_NOT_FOUND,"Room"));

        Cinema cinema = room.getCinema();
        String cinemaName = cinema.getName();
        Showtime showtime = showtimeMapper.toShow(showtimeRequest);
        showtime.setRoom(room);
        showtime.setMovie(movie);
        ShowtimeResponse showtimeResponse = showtimeMapper.toShowtimeResponse(showtimeRepository.save(showtime));
        showtimeResponse.setCinema(cinemaName);
        showtimeResponse.setMovie(movie.getTitle());
        showtimeResponse.setRoom(room.getName());
        return showtimeResponse;
    }

    public List<ShowtimeResponse> getShowtimeByCinemaId( String cinemaId, String movieId){
        return showtimeRepository.findShowtimeByCinemaIdAndMovieId(cinemaId, movieId);
    }

    public int countShowtime(String cinemaId, String movieId){
        return showtimeRepository.countShowtimes( cinemaId,  movieId);
    }
}
