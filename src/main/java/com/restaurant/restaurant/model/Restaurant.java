package com.restaurant.restaurant.model;

import com.restaurant.restaurant.enums.PriceRange;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@Schema(description = "Represents a restaurant entity with name, cuisine, address, and price range.")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the restaurant", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Name of the restaurant", example = "La Piazza")
    private String name;

    @Schema(description = "Cuisine type served by the restaurant", example = "Italian")
    private String cuisineType;

    @Schema(description = "Physical address of the restaurant", example = "123 Main Street, Springfield")
    private String address;

    @Schema(description = "Price range of the restaurant", example = "MODERATE")
    private PriceRange priceRange;
}
