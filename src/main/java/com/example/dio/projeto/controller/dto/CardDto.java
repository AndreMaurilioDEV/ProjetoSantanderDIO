package com.example.dio.projeto.controller.dto;

import java.math.BigDecimal;

public record CardDto(Long id, String number, BigDecimal limit) { }
