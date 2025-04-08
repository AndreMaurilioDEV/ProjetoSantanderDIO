package com.example.dio.projeto.service;

import com.example.dio.projeto.domain.entity.Card;
import com.example.dio.projeto.domain.entity.User;
import com.example.dio.projeto.domain.repository.CardRepository;
import com.example.dio.projeto.domain.repository.UserRepository;
import com.example.dio.projeto.service.exception.BusinessException;
import com.example.dio.projeto.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    @Autowired
    public CardService(CardRepository cardRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Card findById(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new NotFoundException("Card not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public Card getCardByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));

        Card card = user.getCard();
        if (card == null) {
            throw new BusinessException("User does not have an associated card.");
        }

        return card;
    }

    @Transactional
    public void updateCardLimitByUser(Long userId, BigDecimal newLimit) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));

        Card card = user.getCard();
        if (card == null) {
            throw new BusinessException("User does not have an associated card.");
        }

        if (newLimit.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("Card limit cannot be negative.");
        }

        card.setLimit(newLimit);
        cardRepository.save(card);
    }

    public void validateCard(Card card) {
        ofNullable(card).orElseThrow(() -> new BusinessException("Card must not be null."));
        ofNullable(card.getNumber()).orElseThrow(() -> new BusinessException("Card number must not be null."));
        ofNullable(card.getLimit()).orElseThrow(() -> new BusinessException("Card limit must not be null."));

        if (cardRepository.existsByNumber(card.getNumber())) {
            throw new BusinessException("This card number already exists.");
        }
    }

}
