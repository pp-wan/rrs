package com.hello.review.api.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
public class RestaurantView {

    private final Long id;
    private final String name;
    private final String address;
    private final ZonedDateTime created_at;
    private final ZonedDateTime updated_at;
}
