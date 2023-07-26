package com.hello.review.repository;

import com.hello.review.model.ReviewEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ReviewRepositoryCustom {

    public Double getAvgScoreByRestaurantId(Long restaurantId);

    public Slice<ReviewEntity> findSliceByRestaurantId(Long restaurantId, Pageable pageable);
}
