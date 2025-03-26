package com.monolithic.cinema.service;

import com.monolithic.cinema.dto.Request.ReviewRequest;
import com.monolithic.cinema.dto.Response.ReviewResponse;
import com.monolithic.cinema.entity.Movie;
import com.monolithic.cinema.entity.Review;
import com.monolithic.cinema.entity.User;
import com.monolithic.cinema.enums.ErrorCode;
import com.monolithic.cinema.exception.CustomException;
import com.monolithic.cinema.mapper.ReviewMapper;
import com.monolithic.cinema.repository.MovieRepository;
import com.monolithic.cinema.repository.ReviewRepository;
import com.monolithic.cinema.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public ReviewResponse createReview(ReviewRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND, "User"));
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND, "Movie"));

        Review review = reviewMapper.toReview(request);
        review.setUser(user);
        review.setMovie(movie);

        return reviewMapper.toReviewResponse(reviewRepository.save(review));
    }

    public List<ReviewResponse> getReviewsByMovie(String movieId) {
        return reviewRepository.findAll().stream()
                .filter(review -> review.getMovie().getId().equals(movieId))
                .map(reviewMapper::toReviewResponse)
                .toList();
    }
}
