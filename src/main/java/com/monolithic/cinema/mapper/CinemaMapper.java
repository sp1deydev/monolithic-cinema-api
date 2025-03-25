package com.monolithic.cinema.mapper;

import com.monolithic.cinema.dto.Request.CinemaRequest;
import com.monolithic.cinema.dto.Request.GenreRequest;
import com.monolithic.cinema.dto.Response.CinemaResponse;
import com.monolithic.cinema.entity.Cinema;
import com.monolithic.cinema.entity.Genre;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = RoomMapper.class)
public interface CinemaMapper {
    Cinema toCinema(CinemaRequest request);
    CinemaResponse toCinemaResponse(Cinema cinema);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        //To ignore null field when mapping
    void updateCinema(@MappingTarget Cinema cinema, CinemaRequest request);
}
