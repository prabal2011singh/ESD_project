package com.tasty.tasty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

public record ProductRequest(
        @NotNull(message = "Product name should be present")
        @NotEmpty(message = "Product name should be present")
        String name,

        @NotNull(message = "Price should be present")
        @Min(value = 0, message = "Price should be positive")
        Double price
) {}