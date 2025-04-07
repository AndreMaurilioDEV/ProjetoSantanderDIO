package com.example.dio.projeto.mapper;

import com.example.dio.projeto.controller.dto.CardDto;
import com.example.dio.projeto.domain.entity.Card;
import org.mapstruct.factory.Mappers;

public interface CardMapper {

    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    CardDto toDto(Card card);
}
