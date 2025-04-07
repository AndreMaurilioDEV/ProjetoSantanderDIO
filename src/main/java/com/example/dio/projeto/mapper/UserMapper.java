package com.example.dio.projeto.mapper;

import com.example.dio.projeto.controller.dto.UserCreationDto;
import com.example.dio.projeto.controller.dto.UserDto;
import com.example.dio.projeto.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);
    User toEntity(UserCreationDto userCreationDto);
}
