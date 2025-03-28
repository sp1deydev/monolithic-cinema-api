package com.monolithic.cinema.dto.Request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketRequest {
    String Id;

    String bookingId;

    String seatId;
}
