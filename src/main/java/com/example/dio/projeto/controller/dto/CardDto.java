package com.example.dio.projeto.controller.dto;

import com.example.dio.projeto.domain.entity.Card;

import java.math.BigDecimal;

public record CardDto(Long id, String number, BigDecimal limit) {
}
