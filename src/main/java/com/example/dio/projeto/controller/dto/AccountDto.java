package com.example.dio.projeto.controller.dto;

import com.example.dio.projeto.domain.entity.Account;

import java.math.BigDecimal;

public record AccountDto(Long id, String number, String agency, BigDecimal balance, BigDecimal limit) { }
