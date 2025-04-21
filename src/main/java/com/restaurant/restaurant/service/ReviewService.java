package com.restaurant.restaurant.service;

import com.restaurant.restaurant.model.Restaurant;
import com.restaurant.restaurant.model.Review;
import com.restaurant.restaurant.repository.RestaurantRepository;
import com.restaurant.restaurant.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Double getAverageRating(Long restaurantId) {
        return reviewRepository.findAverageRatingByRestaurantId(restaurantId);
    }

    // Create a new review
    public Review submitReview(Review review, Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant with ID "+restaurantId+" not found."));

//        review.setRestaurant(restaurant);
        return reviewRepository.save(review);
    }

    // Retrieve all reviews for a restaurant
    public List<Review> getReviewsByRestaurant(Long restaurantId) {
        return reviewRepository.findByRestaurantId(restaurantId);
    }

    // Retrieve review by ID
    public Optional<Review> getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId);
    }

    // Update a review
    public Review updateReview(Long reviewId, Review updatedReview) {
        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
//        existingReview.setRating(updatedReview.getRating());
//        existingReview.setComment(updatedReview.getComment());
        return reviewRepository.save(existingReview);
    }

    // Delete a review
    public void deleteReview(Long reviewId) {
        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        reviewRepository.delete(existingReview);
    }
}

