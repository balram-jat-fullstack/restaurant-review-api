package com.restaurant.restaurant.service;

import com.restaurant.restaurant.model.Restaurant;
import com.restaurant.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getTopRestaurantsByCuisine(String cuisineType) {
        return restaurantRepository.findTop3ByCuisineTypeOrderByName(cuisineType);
    }
}
