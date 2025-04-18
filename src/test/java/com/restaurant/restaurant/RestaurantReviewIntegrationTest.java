package com.restaurant.restaurant;

import com.restaurant.restaurant.enums.ReviewStatus;
import com.restaurant.restaurant.model.Restaurant;
import com.restaurant.restaurant.model.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantReviewIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private Restaurant italianRestaurant;
    private Review review;

    @BeforeEach
    public void setUp() {
        italianRestaurant = new Restaurant();
        italianRestaurant.setName("Italian Bistro");
        italianRestaurant.setCuisineType("Italian");

        review = new Review();
        review.setRating(5);
        review.setComment("Excellent!");
        review.setStatus(ReviewStatus.APPROVED);
    }

    @Test
    public void testGetTopRestaurants() {
        ResponseEntity<Restaurant[]> response = restTemplate.getForEntity("/api/restaurants/top/Italian", Restaurant[].class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Italian Bistro", response.getBody()[0].getName());
    }

    @Test
    public void testGetAverageRating() {
        ResponseEntity<Double> response = restTemplate.getForEntity("/api/reviews/average/1", Double.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(4.5, response.getBody());
    }
}
