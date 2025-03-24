package com.monolithic.cinema.service;

import com.monolithic.cinema.dto.Request.MovieRequest;
import com.monolithic.cinema.dto.Response.MovieResponse;
import com.monolithic.cinema.entity.Movie;
import com.monolithic.cinema.mapper.MovieMapper;
import com.monolithic.cinema.repository.MovieRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieService {
    MovieRepository movieRepository;
    MovieMapper movieMapper;

    public List<MovieResponse> getMovies() {
        return movieRepository.findAll().stream().map(movieMapper::toMovieResponse).toList();
    }

    public MovieResponse getMovie(String id) {
        return movieMapper.toMovieResponse(movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie Not Found")));
    }

    public int countMovies() {
        return (int) movieRepository.count();
    }

    public MovieResponse createMovie(MovieRequest request) {
        Movie movie = movieMapper.toMovie(request);
        return movieMapper.toMovieResponse(movieRepository.save(movie));
    }

    public MovieResponse updateMovie(String id, MovieRequest request) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie Not Found"));
        movieMapper.updateMovie(movie, request);
        return movieMapper.toMovieResponse(movieRepository.save(movie));
    }

    public void deleteMovie(String id) {
        movieRepository.deleteById(id);
    }
}
