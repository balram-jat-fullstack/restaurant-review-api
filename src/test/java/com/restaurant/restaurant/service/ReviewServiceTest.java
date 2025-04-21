package com.restaurant.restaurant.service;

import com.restaurant.restaurant.model.Restaurant;
import com.restaurant.restaurant.model.Review;
import com.restaurant.restaurant.repository.RestaurantRepository;
import com.restaurant.restaurant.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    private Restaurant restaurant;
    private Review review;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurant = new Restaurant(); // Assuming a basic no-arg constructor
        restaurant.setId(1L);
        restaurant.setName("Test Restaurant");

        review = new Review(); // Assuming a basic no-arg constructor
        review.setId(1L);
        review.setComment("Great food!");
        review.setRating(5);
        review.setRestaurant(restaurant);
    }

    @Test
    void testSubmitReview_Success() {
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));
        when(reviewRepository.save(review)).thenReturn(review);

        Review submittedReview = reviewService.submitReview(review, 1L);

        assertNotNull(submittedReview);
        assertEquals(review.getComment(), submittedReview.getComment());
        verify(restaurantRepository, times(1)).findById(1L);
        verify(reviewRepository, times(1)).save(review);
    }

    @Test
    void testSubmitReview_RestaurantNotFound() {
        when(restaurantRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            reviewService.submitReview(review, 1L);
        });

        assertEquals("Restaurant with ID 1 not found.", exception.getMessage());
        verify(restaurantRepository, times(1)).findById(1L);
        verify(reviewRepository, never()).save(any());
    }

    @Test
    void testGetAverageRating() {
        when(reviewRepository.findAverageRatingByRestaurantId(1L)).thenReturn(4.5);

        Double averageRating = reviewService.getAverageRating(1L);

        assertEquals(4.5, averageRating);
        verify(reviewRepository, times(1)).findAverageRatingByRestaurantId(1L);
    }

    @Test
    void testDeleteReview_Success() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        doNothing().when(reviewRepository).delete(review);

        assertDoesNotThrow(() -> reviewService.deleteReview(1L));

        verify(reviewRepository, times(1)).findById(1L);
        verify(reviewRepository, times(1)).delete(review);
    }

    @Test
    void testDeleteReview_ReviewNotFound() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            reviewService.deleteReview(1L);
        });

        assertEquals("Review not found", exception.getMessage());
        verify(reviewRepository, times(1)).findById(1L);
        verify(reviewRepository, never()).delete(any());
    }
}