package com.monolithic.cinema.dto.Response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class GenreDetailResponse {

    String id;
    String name;
    List<MovieResponse> movies;

}
