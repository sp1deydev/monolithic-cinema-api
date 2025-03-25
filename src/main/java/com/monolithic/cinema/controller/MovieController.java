package com.monolithic.cinema.controller;

import com.monolithic.cinema.dto.Request.MovieRequest;
import com.monolithic.cinema.dto.Response.MovieResponse;
import com.monolithic.cinema.service.MovieService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/movies")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieController {
    MovieService movieService;
    private static final String DEFAULT_MOVIES_PAGE_SIZE = "12";
    @Transactional

    public List<MovieResponse> getMovies() {
        return movieService.getMovies();
    }

    @GetMapping("/{id}")
    public MovieResponse getMovie(@PathVariable String id) {
        return movieService.getMovie(id);
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity countMovies(@RequestParam(defaultValue = DEFAULT_MOVIES_PAGE_SIZE) int size) {
        int totalMovies = movieService.countMovies();
        int totalPages = (int) Math.ceil((double) totalMovies / size);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(totalMovies));
        headers.add("X-Total-Pages", String.valueOf(totalPages));
        headers.add("X-Page-Size", String.valueOf(size));

        return ResponseEntity.ok().headers(headers).build();
    }

    @PostMapping
    public MovieResponse createMovie(@RequestBody MovieRequest request) {
        return movieService.createMovie(request);
    }

    @PutMapping("/{id}")
    public MovieResponse updateMovie(@PathVariable String id, @RequestBody MovieRequest request) {
        return movieService.updateMovie(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable String id) {
        movieService.deleteMovie(id);
    }
}
