package com.example.dio.projeto.controller.dto;

import com.example.dio.projeto.domain.entity.*;

import java.util.List;

public record UserDto(
        Long id,
        String name,
        Account account,
        Card card,
        List<Feature> features,
        List<News> news
) {}


