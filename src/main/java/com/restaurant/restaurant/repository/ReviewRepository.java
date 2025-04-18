package com.restaurant.restaurant.repository;

import com.restaurant.restaurant.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRestaurantId(Long restaurantId);
    Double findAverageRatingByRestaurantId(Long restaurantId);
}
