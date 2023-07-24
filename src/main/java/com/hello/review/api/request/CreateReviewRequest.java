package com.hello.review.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateReviewRequest {

    private final Long restaurantId;
    private String content;
    private Double score;
}
