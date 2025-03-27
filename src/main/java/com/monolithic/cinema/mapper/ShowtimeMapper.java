package com.monolithic.cinema.mapper;

import com.monolithic.cinema.dto.Request.ShowtimeRequest;
import com.monolithic.cinema.dto.Response.ShowtimeResponse;
import com.monolithic.cinema.entity.Showtime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShowtimeMapper {

    Showtime toShow(ShowtimeRequest showtimeRequest);

    @Mapping(source = "movie.id",target = "movie")
    @Mapping(source = "room.id", target = "room")
    ShowtimeResponse toShowtimeResponse(Showtime showtime);
}
