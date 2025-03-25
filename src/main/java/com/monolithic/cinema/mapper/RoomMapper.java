package com.monolithic.cinema.mapper;

import com.monolithic.cinema.dto.Request.MovieRequest;
import com.monolithic.cinema.dto.Request.RoomRequest;
import com.monolithic.cinema.dto.Response.RoomResponse;
import com.monolithic.cinema.entity.Movie;
import com.monolithic.cinema.entity.Room;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = SeatMapper.class)
public interface RoomMapper {
    @Mapping(source = "cinema.name", target = "cinemaName")
//    @Mapping(source = "seats", target = "seats")
    RoomResponse toRoomResponse(Room room);

    @Mapping(source = "cinema.name", target = "cinemaName")
    @Mapping(target = "seats", ignore = true)
    RoomResponse toListRoomResponse(Room room);

    Room toRoom(RoomRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        //To ignore null field when mapping
    void updateRoom(@MappingTarget Room room, RoomRequest request);
}
