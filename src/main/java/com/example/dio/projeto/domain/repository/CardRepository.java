package com.example.dio.projeto.domain.repository;

import com.example.dio.projeto.domain.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    boolean existsByNumber(String number);
}
