package com.example.dio.projeto.mapper;

import com.example.dio.projeto.controller.dto.FeatureDto;
import com.example.dio.projeto.domain.entity.Feature;
import org.mapstruct.factory.Mappers;

public interface FeatureMapper {

    FeatureMapper INSTANCE = Mappers.getMapper(FeatureMapper.class);

    FeatureDto toDto(Feature feature);
}

