package com.monolithic.cinema.controller;

import com.monolithic.cinema.dto.Request.GenreRequest;
import com.monolithic.cinema.dto.Response.GenreResponse;
import com.monolithic.cinema.service.GenreService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/genres")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreController {
    GenreService genreService;
    @Transactional
    @GetMapping
    public List<GenreResponse> getGenres() {
        return genreService.getGenres();
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity countGenres() {
        int totalGenres = genreService.countGenres();

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(totalGenres));

        return ResponseEntity.ok().headers(headers).build();
    }
    @Transactional
    @GetMapping("/{id}")
    public GenreResponse getGenre(@PathVariable String id) {
        return genreService.getGenre(id);
    }
    @Transactional
    @PostMapping
    public GenreResponse createGenre(@RequestBody GenreRequest request) {
        return genreService.createGenre(request);
    }
    @Transactional
    @PutMapping("/{id}")
    public GenreResponse updateGenre(@PathVariable String id, @RequestBody GenreRequest request) {
        return genreService.updateGenre(id, request);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable String id) {
        genreService.deleteGenre(id);
    }
}
