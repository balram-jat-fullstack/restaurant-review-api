package com.restaurant.restaurant.service;

import com.restaurant.restaurant.model.Restaurant;
import com.restaurant.restaurant.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    private Restaurant italianRestaurant;
    private Restaurant mexicanRestaurant;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initializes the mocks

        italianRestaurant = new Restaurant();
        italianRestaurant.setId(1L);
        italianRestaurant.setName("Italian Bistro");
        italianRestaurant.setCuisineType("Italian");

        mexicanRestaurant = new Restaurant();
        mexicanRestaurant.setId(2L);
        mexicanRestaurant.setName("Mexican Grill");
        mexicanRestaurant.setCuisineType("Mexican");
    }

    @Test
    public void testGetTopRestaurantsByCuisine() {
        // Mock the repository behavior
        when(restaurantRepository.findTop3ByCuisineTypeOrderByName("Italian")).thenReturn(Arrays.asList(italianRestaurant));

        List<Restaurant> topItalianRestaurants = restaurantService.getTopRestaurantsByCuisine("Italian");

        assertEquals(1, topItalianRestaurants.size());
        assertEquals("Italian Bistro", topItalianRestaurants.get(0).getName());
    }
}
