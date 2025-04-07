package com.example.dio.projeto.mapper;

import com.example.dio.projeto.controller.dto.NewsDto;
import com.example.dio.projeto.domain.entity.News;
import org.mapstruct.factory.Mappers;

public interface NewsMapper {

    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsDto toDto(News news);

}
