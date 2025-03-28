package com.monolithic.cinema.dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class BookingResponse {
    String id;
    String user;
    String showtime;
    String status;
    List<TicketResponse> tickets;
}
