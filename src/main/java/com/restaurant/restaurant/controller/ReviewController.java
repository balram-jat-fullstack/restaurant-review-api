package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@Tag(name = "Reviews", description = "Endpoints for retrieving restaurant reviews and ratings")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Operation(summary = "Get average rating for a restaurant", description = "Returns the average rating for a given restaurant ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved average rating"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/average/{restaurantId}")
    public ResponseEntity<Double> getAverageRating(
            @Parameter(description = "ID of the restaurant", example = "101")
            @PathVariable Long restaurantId) {
        return ResponseEntity.ok(reviewService.getAverageRating(restaurantId));
    }
}
