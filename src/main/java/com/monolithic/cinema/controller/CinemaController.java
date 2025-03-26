package com.monolithic.cinema.controller;

import com.monolithic.cinema.dto.Request.CinemaRequest;
import com.monolithic.cinema.dto.Response.CinemaResponse;
import com.monolithic.cinema.service.CinemaService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cinemas")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CinemaController {
    CinemaService cinemaService;
    private final static String DEFAULT_CINEMAS_PAGE_SIZE = "12";


    @GetMapping
    public List<CinemaResponse> getCinemas() {
        return cinemaService.getCinemas();
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity countCinemas(@RequestParam(defaultValue = DEFAULT_CINEMAS_PAGE_SIZE) int size) {
        int totalCinemas = cinemaService.countCinemas();
        int totalPages = (int) Math.ceil((double) totalCinemas / size);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(totalCinemas));
        headers.add("X-Total-Pages", String.valueOf(totalPages));
        headers.add("X-Page-Size", String.valueOf(size));

        return ResponseEntity.ok().headers(headers).build();
    }

    @GetMapping("/{id}")
    public CinemaResponse getCinema(@PathVariable String id) {
        return cinemaService.getCinema(id);
    }

    @PostMapping
    public CinemaResponse createCinema(@RequestBody CinemaRequest request) {
        return cinemaService.createCinema(request);
    }

    @PutMapping("/{id}")
    public CinemaResponse updateCinema(@PathVariable String id, @RequestBody CinemaRequest request) {
        return cinemaService.updateCinema(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteCinema(@PathVariable String id) {
        cinemaService.deleteCinema(id);
    }
}
