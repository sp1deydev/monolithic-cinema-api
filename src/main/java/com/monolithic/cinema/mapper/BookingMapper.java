package com.monolithic.cinema.mapper;

import com.monolithic.cinema.dto.Request.BookingRequest;
import com.monolithic.cinema.dto.Response.BookingResponse;
import com.monolithic.cinema.entity.Booking;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = TicketMapper.class)
public interface BookingMapper {

    @Mapping(source = "user.id", target = "user")
    @Mapping(source = "showtime.id", target = "showtime")
    BookingResponse toBookingResponse(Booking booking);

    //Booking toBooking(BookingRequest request);
//
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//        //To ignore null field when mapping
//    void updateBooking(@MappingTarget Booking booking, BookingRequest request);
}
