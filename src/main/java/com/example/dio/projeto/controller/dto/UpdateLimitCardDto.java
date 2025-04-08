package com.example.dio.projeto.controller.dto;

import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;

public record UpdateLimitCardDto(
        @DecimalMin(value = "0.00", inclusive = true, message = "Limit must be positive")
        BigDecimal limit
) {
}
