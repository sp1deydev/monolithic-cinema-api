package com.monolithic.cinema.dto.Request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RoomRequest {
    String name;
    String cinemaId;
}
