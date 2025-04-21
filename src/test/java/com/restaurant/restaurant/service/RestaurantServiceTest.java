package com.restaurant.restaurant.service;

import com.restaurant.restaurant.model.Restaurant;
import com.restaurant.restaurant.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Test Restaurant");
        restaurant.setAddress("123 Test Street"); // Assuming an address field
    }

    @Test
    void testGetRestaurantById_Success() {
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));

        Optional<Restaurant> result = restaurantService.getRestaurantById(1L);

        assertTrue(result.isPresent());
        assertEquals("Test Restaurant", result.get().getName());
        verify(restaurantRepository, times(1)).findById(1L);
    }

    @Test
    void testGetRestaurantById_NotFound() {
        when(restaurantRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Restaurant> result = restaurantService.getRestaurantById(1L);

        assertTrue(result.isEmpty());
        verify(restaurantRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllRestaurants() {
        Restaurant restaurant2 = new Restaurant();
        restaurant2.setId(2L);
        restaurant2.setName("Another Restaurant");
        restaurant2.setAddress("456 Another Street");

        when(restaurantRepository.findAll()).thenReturn(Arrays.asList(restaurant, restaurant2));

        List<Restaurant> result = restaurantService.getAllRestaurants();

        assertEquals(2, result.size());
        verify(restaurantRepository, times(1)).findAll();
    }

    @Test
    void testSaveRestaurant() {
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);

        Restaurant result = restaurantService.createRestaurant(restaurant);

        assertNotNull(result);
        assertEquals("Test Restaurant", result.getName());
        verify(restaurantRepository, times(1)).save(restaurant);
    }
}