package com.monolithic.cinema.service;

import com.monolithic.cinema.dto.Request.MovieRequest;
import com.monolithic.cinema.dto.Response.MovieResponse;
import com.monolithic.cinema.entity.Movie;
import com.monolithic.cinema.enums.ErrorCode;
import com.monolithic.cinema.exception.CustomException;
import com.monolithic.cinema.mapper.MovieMapper;
import com.monolithic.cinema.repository.GenreRepository;
import com.monolithic.cinema.repository.MovieRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieService {
    MovieRepository movieRepository;
    GenreRepository genreRepository;

    MovieMapper movieMapper;

    public List<MovieResponse> getMovies() {
        return movieRepository.findAll().stream().map(movieMapper::toMovieResponse).toList();
    }

    public MovieResponse getMovie(String id) {
        return movieMapper.toMovieResponse(movieRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND, "Movie")));
    }

    public int countMovies() {
        return (int) movieRepository.count();
    }

    @Transactional()
    public MovieResponse createMovie(MovieRequest request) {
        if(movieRepository.existsByTitle(request.getTitle())) {
            throw new CustomException(ErrorCode.RESOURCE_ALREADY_EXISTS, "Movie");
        }
        var genres = genreRepository.findById(request.getGenreId())
                .orElseThrow(()-> new CustomException(ErrorCode.RESOURCE_NOT_FOUND,"Genre"));
        Movie movie = movieMapper.toMovie(request);
        movie.setGenre(genres);
        return movieMapper.toMovieResponse(movieRepository.save(movie));
    }

    public MovieResponse updateMovie(String id, MovieRequest request) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND, "Movie"));
        movieMapper.updateMovie(movie, request);
        return movieMapper.toMovieResponse(movieRepository.save(movie));
    }

    public void deleteMovie(String id) {
        movieRepository.deleteById(id);
    }
}
