package com.monolithic.cinema.controller;

import com.monolithic.cinema.dto.Request.ReviewRequest;
import com.monolithic.cinema.dto.Response.ReviewResponse;
import com.monolithic.cinema.service.ReviewService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/reviews")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewController {
    ReviewService reviewService;

    @Transactional
    @PostMapping("/{movieId}")
    public ReviewResponse createReview(@PathVariable String movieId, @RequestBody ReviewRequest request) {
        request.setMovieId(movieId);
        return reviewService.createReview(request);
    }

//    @PostMapping("/{movieId}")
//    public ResponseEntity<ReviewResponse> addReview(@PathVariable String movieId, @RequestBody ReviewRequest request) {
//        request.setMovieId(movieId);  // Đảm bảo request chứa đúng movieId
//        return ResponseEntity.ok(reviewService.addReview(request));
//    }

    @Transactional
    @GetMapping("/{movieId}")
    public List<ReviewResponse> getReviewsByMovie(@PathVariable String movieId) {
        return reviewService.getReviewsByMovie(movieId);
    }
}
