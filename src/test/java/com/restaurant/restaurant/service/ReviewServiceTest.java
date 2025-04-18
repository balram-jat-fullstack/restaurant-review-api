package com.restaurant.restaurant.service;

import com.restaurant.restaurant.enums.ReviewStatus;
import com.restaurant.restaurant.model.Review;
import com.restaurant.restaurant.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    private Review review1;
    private Review review2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initializes the mocks

        review1 = new Review();
        review1.setRating(5);
        review1.setComment("Excellent!");
        review1.setStatus(ReviewStatus.APPROVED);

        review2 = new Review();
        review2.setRating(4);
        review2.setComment("Good food, but service was slow.");
        review2.setStatus(ReviewStatus.APPROVED);
    }

    @Test
    public void testGetAverageRating() {
        // Mock the repository behavior
        when(reviewRepository.findAverageRatingByRestaurantId(1L)).thenReturn(4.5);

        Double averageRating = reviewService.getAverageRating(1L);

        assertEquals(4.5, averageRating);
    }
}
