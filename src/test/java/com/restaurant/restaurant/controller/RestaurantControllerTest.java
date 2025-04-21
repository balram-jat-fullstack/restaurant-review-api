package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.model.Restaurant;
import com.restaurant.restaurant.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RestaurantService restaurantService;

    private RestaurantController restaurantController;

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantController = new RestaurantController();
        restaurantController.restaurantService = restaurantService;
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();

        restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Test Restaurant");
        restaurant.setAddress("123 Test Street");
    }

    @Test
    void testGetRestaurantById_Success() throws Exception {
        when(restaurantService.getRestaurantById(1L)).thenReturn(Optional.of(restaurant));

        mockMvc.perform(get("/api/restaurants/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Restaurant"))
                .andExpect(jsonPath("$.address").value("123 Test Street"));

        verify(restaurantService, times(1)).getRestaurantById(1L);
    }

    @Test
    void testGetRestaurantById_NotFound() throws Exception {
        when(restaurantService.getRestaurantById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/restaurants/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(restaurantService, times(1)).getRestaurantById(1L);
    }

    @Test
    void testGetAllRestaurants() throws Exception {
        Restaurant restaurant2 = new Restaurant();
        restaurant2.setId(2L);
        restaurant2.setName("Another Restaurant");
        restaurant2.setAddress("456 Another Street");

        when(restaurantService.getAllRestaurants()).thenReturn(Arrays.asList(restaurant, restaurant2));

        mockMvc.perform(get("/api/restaurants").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Test Restaurant"))
                .andExpect(jsonPath("$[1].name").value("Another Restaurant"));

        verify(restaurantService, times(1)).getAllRestaurants();
    }

    @Test
    void testSaveRestaurant() throws Exception {
        when(restaurantService.createRestaurant(any(Restaurant.class))).thenReturn(restaurant);

        mockMvc.perform(post("/api/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Test Restaurant\", \"address\": \"123 Test Street\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Restaurant"))
                .andExpect(jsonPath("$.address").value("123 Test Street"));

        verify(restaurantService, times(1)).createRestaurant(any(Restaurant.class));
    }
}