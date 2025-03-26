package com.monolithic.cinema.dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.monolithic.cinema.entity.Seat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RoomResponse {
    String id;
    String name;
    String cinemaName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<SeatResponse> seats;
}
