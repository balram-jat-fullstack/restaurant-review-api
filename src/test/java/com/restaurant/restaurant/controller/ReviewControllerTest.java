package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.model.Review;
import com.restaurant.restaurant.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ReviewService reviewService;

    private ReviewController reviewController;

    private Review review;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reviewController = new ReviewController();
        reviewController.reviewService = reviewService;
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        // Create a dummy review
        review = new Review();
        review.setId(1L);
        review.setComment("Excellent!");
        review.setRating(5);
    }

    @Test
    void testGetReviewsByRestaurant_Success() throws Exception {
        when(reviewService.getReviewsByRestaurant(1L)).thenReturn(Collections.singletonList(review));

        mockMvc.perform(get("/api/reviews/restaurant/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].comment").value("Excellent!"))
                .andExpect(jsonPath("$[0].rating").value(5));

        verify(reviewService, times(1)).getReviewsByRestaurant(1L);
    }

    @Test
    void testSubmitReview_Success() throws Exception {
        when(reviewService.submitReview(any(Review.class), eq(1L))).thenReturn(review);

        mockMvc.perform(post("/api/reviews/restaurant/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"comment\": \"Excellent!\", \"rating\": 4.5 }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.comment").value("Excellent!"))
                .andExpect(jsonPath("$.rating").value(5));

        verify(reviewService, times(1)).submitReview(any(Review.class), eq(1L));
    }

    @Test
    void testGetReviewById_Success() throws Exception {
        when(reviewService.getReviewById(1L)).thenReturn(Optional.of(review));

        mockMvc.perform(get("/api/reviews/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment").value("Excellent!"))
                .andExpect(jsonPath("$.rating").value(5));

        verify(reviewService, times(1)).getReviewById(1L);
    }

    @Test
    void testGetReviewById_NotFound() throws Exception {
        when(reviewService.getReviewById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/reviews/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(reviewService, times(1)).getReviewById(1L);
    }
}