package com.monolithic.cinema.mapper;

import com.monolithic.cinema.dto.Response.SeatResponse;
import com.monolithic.cinema.entity.Seat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    SeatResponse toSeatResponse(Seat seat);
}
