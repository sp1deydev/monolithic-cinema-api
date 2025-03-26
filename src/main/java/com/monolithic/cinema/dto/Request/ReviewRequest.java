package com.monolithic.cinema.dto.Request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewRequest {
    String id;
    String userId;
    String movieId;
    Integer rating;
    String comment;
}
