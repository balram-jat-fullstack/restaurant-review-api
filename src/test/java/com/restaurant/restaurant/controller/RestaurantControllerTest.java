package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.model.Restaurant;
import com.restaurant.restaurant.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
public class RestaurantControllerTest {

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private RestaurantController restaurantController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initializes the mocks

        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
    }

    @Test
    public void testGetTopRestaurants() throws Exception {
        Restaurant italianRestaurant = new Restaurant();
        italianRestaurant.setId(1L);
        italianRestaurant.setName("Italian Bistro");
        italianRestaurant.setCuisineType("Italian");

        when(restaurantService.getTopRestaurantsByCuisine("Italian")).thenReturn(java.util.List.of(italianRestaurant));

        mockMvc.perform(get("/api/restaurants/top/Italian"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Italian Bistro"));
    }
}
