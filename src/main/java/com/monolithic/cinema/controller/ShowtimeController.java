package com.monolithic.cinema.controller;

import com.monolithic.cinema.dto.Request.ShowtimeRequest;
import com.monolithic.cinema.dto.Response.ShowtimeResponse;
import com.monolithic.cinema.entity.Showtime;
import com.monolithic.cinema.service.ShowtimeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/v1/showtimes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShowtimeController {

    ShowtimeService showtimeService;

     static final String DEFAULT_SHOWTIMES_PAGE_SIZE = "10";
    @PostMapping
    public ShowtimeResponse createShowtime (@RequestBody ShowtimeRequest showtimeRequest){
        return showtimeService.createShowtime(showtimeRequest);
    }

    @GetMapping
    public List<ShowtimeResponse> getShowtimeByCinemaId(@RequestParam(required = false) String cinemaId, @RequestParam(required = false) String movieId){
        return showtimeService.getShowtimeByCinemaId(cinemaId,movieId);
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity countShowtimes(@RequestParam(defaultValue = DEFAULT_SHOWTIMES_PAGE_SIZE) int size,@RequestParam(required = false) String cinemaId, @RequestParam(required = false) String movieId) {
        int totalShowtime = showtimeService.countShowtime(cinemaId,movieId);
        int totalPages = (int) Math.ceil((double) totalShowtime / size);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(totalShowtime));
        headers.add("X-Total-Pages", String.valueOf(totalPages));
        headers.add("X-Page-Size", String.valueOf(size));

        return ResponseEntity.ok().headers(headers).build();
    }

}
