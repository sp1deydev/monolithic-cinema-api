package com.monolithic.cinema.dto.Response;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class MovieResponse {

    String id;
    String title;
    String description;
    LocalDate releaseDate;
    Integer duration;
    String genre;

}
