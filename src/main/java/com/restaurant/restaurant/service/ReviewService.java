package com.restaurant.restaurant.service;

import com.restaurant.restaurant.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Double getAverageRating(Long restaurantId) {
        return reviewRepository.findAverageRatingByRestaurantId(restaurantId);
    }
}
