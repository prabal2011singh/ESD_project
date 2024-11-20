package com.tasty.tasty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductResponse(
        Long id,
        String name,
        Double price
) {}