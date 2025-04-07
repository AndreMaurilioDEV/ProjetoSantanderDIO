package com.example.dio.projeto.controller.dto;

import com.example.dio.projeto.domain.entity.Account;
import com.example.dio.projeto.domain.entity.Card;
import com.example.dio.projeto.domain.entity.Feature;
import com.example.dio.projeto.domain.entity.News;
import com.example.dio.projeto.domain.entity.User;

import java.util.List;

public record UserCreationDto(
        String name,
        Account account,
        Card card,
        List<Feature> features,
        List<News> news) { }

