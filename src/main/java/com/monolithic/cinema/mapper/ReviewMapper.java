package com.monolithic.cinema.mapper;

import com.monolithic.cinema.dto.Request.ReviewRequest;
import com.monolithic.cinema.dto.Response.ReviewResponse;
import com.monolithic.cinema.entity.Review;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(source = "user.id", target = "user")
    @Mapping(source = "movie.id", target = "movie")

    ReviewResponse toReviewResponse(Review review);

    Review toReview(ReviewRequest request);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        //To ignore null field when mapping
    void updateReview(@MappingTarget Review review, ReviewRequest request);
}
