package com.monolithic.cinema.mapper;

import com.monolithic.cinema.dto.Request.TicketRequest;
import com.monolithic.cinema.dto.Response.TicketResponse;
import com.monolithic.cinema.entity.Ticket;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface TicketMapper {
    //@Mapping(source = "booking.id", target = "booking")
    @Mapping(source = "seat.id", target = "seatId")
    TicketResponse toTicketResponse(Ticket ticket);

//    Ticket toTicket(TicketRequest request);
//
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//        //To ignore null field when mapping
//    void updateTicket(@MappingTarget Ticket ticket, TicketRequest request);
}
