package com.monolithic.cinema.dto.Request;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRequest {
    String userId;
    String showtimeId;
    List<String> seatId;
}
