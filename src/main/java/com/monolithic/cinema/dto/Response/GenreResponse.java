package com.monolithic.cinema.dto.Response;

import com.monolithic.cinema.entity.Movie;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class GenreResponse {

    String id;
    String name;

}

