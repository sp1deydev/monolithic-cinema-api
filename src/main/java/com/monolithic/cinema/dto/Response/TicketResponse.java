package com.monolithic.cinema.dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TicketResponse {
    String id;

//    String booking;

    String seatId;
}
