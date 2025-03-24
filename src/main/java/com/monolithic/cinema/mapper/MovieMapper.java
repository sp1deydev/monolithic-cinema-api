package com.monolithic.cinema.mapper;

import com.monolithic.cinema.dto.Request.GenreRequest;
import com.monolithic.cinema.dto.Request.MovieRequest;
import com.monolithic.cinema.dto.Response.MovieResponse;
import com.monolithic.cinema.entity.Genre;
import com.monolithic.cinema.entity.Movie;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    @Mapping(source = "genre.name", target = "genre")
    MovieResponse toMovieResponse(Movie movie);

    Movie toMovie(MovieRequest request);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    //To ignore null field when mapping
    void updateMovie(@MappingTarget Movie movie, MovieRequest request);
}
