package com.monolithic.cinema.mapper;

import com.monolithic.cinema.dto.Request.GenreRequest;
import com.monolithic.cinema.dto.Response.GenreDetailResponse;
import com.monolithic.cinema.dto.Response.GenreResponse;
import com.monolithic.cinema.entity.Genre;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = MovieMapper.class)
public interface GenreMapper {
    Genre toGenre(GenreRequest request);

    GenreResponse toGenreResponse(Genre genre);

    GenreDetailResponse toGenreDetailResponse(Genre genre);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateGenre(@MappingTarget Genre genre, GenreRequest request);
}
