package com.restaurant.restaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.restaurant.restaurant.enums.ReviewStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
@Entity
@Schema(description = "Represents a review submitted by a customer for a restaurant.")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the review", example = "1001", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Rating given by the customer (1 to 5)", example = "4")
    private Integer rating;

    @Schema(description = "Optional comment provided by the customer", example = "Great ambiance and food!")
    private String comment;

    @Schema(description = "Date of the restaurant visit", example = "2024-12-15")
    private LocalDate visitDate;

    @Schema(description = "Status of the review (e.g., PENDING, APPROVED, REJECTED)", example = "APPROVED")
    private ReviewStatus status;

    @ManyToOne
    @Schema(description = "The restaurant that the review is associated with")
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonBackReference
    private Restaurant restaurant;
}
