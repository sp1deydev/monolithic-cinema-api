package com.monolithic.cinema.dto.Request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class MovieRequest {

    String id;
    String title;
    String description;
    LocalDate releaseDate;
    Integer duration;
    String genreId;

}
