package com.example.dio.projeto.mapper;

import com.example.dio.projeto.controller.dto.AccountDto;
import com.example.dio.projeto.domain.entity.Account;
import org.mapstruct.factory.Mappers;

public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto toDto(Account account);
}
