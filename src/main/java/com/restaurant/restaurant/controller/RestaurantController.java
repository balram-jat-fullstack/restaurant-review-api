package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.model.Restaurant;
import com.restaurant.restaurant.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@Tag(name = "Restaurants", description = "Endpoints for retrieving restaurant data")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Operation(summary = "Get top restaurants by cuisine type", description = "Returns a list of top restaurants filtered by the given cuisine type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of restaurants"),
            @ApiResponse(responseCode = "400", description = "Invalid cuisine type supplied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/top/{cuisineType}")
    public ResponseEntity<List<Restaurant>> getTopRestaurants(
                @Parameter(description = "Cuisine type to filter top restaurants by", example = "Italian")
            @PathVariable String cuisineType) {
        return ResponseEntity.ok(restaurantService.getTopRestaurantsByCuisine(cuisineType));
    }
}
