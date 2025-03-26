package com.monolithic.cinema.dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ReviewResponse {
    String id;
    String user;
    String movie;
    Integer rating;
    String comment;
}
