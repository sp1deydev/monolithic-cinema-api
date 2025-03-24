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

//    List<MovieResponse> toMovieResponses(List<Movie> movie);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMovie(@MappingTarget Movie movie, MovieRequest request);
}
