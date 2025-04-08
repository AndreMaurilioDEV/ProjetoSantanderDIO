package com.example.dio.projeto.service;

import com.example.dio.projeto.controller.dto.UserCreationDto;
import com.example.dio.projeto.domain.entity.User;
import com.example.dio.projeto.domain.repository.UserRepository;
import com.example.dio.projeto.service.exception.BusinessException;
import com.example.dio.projeto.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static java.util.Optional.ofNullable;

@Service
public class UserService {

    private static final Long UNCHANGEABLE_USER_ID = 1L;

    private final UserRepository userRepository;
    private final CardService cardService;

    @Autowired
    public UserService(UserRepository userRepository, CardService cardService) {
        this.userRepository = userRepository;
        this.cardService = cardService;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
    }

    @Transactional(readOnly = false)
    public User create(User user) {
        ofNullable(user).orElseThrow(() -> new BusinessException("User to create must not be null."));
        ofNullable(user.getAccount()).orElseThrow(() -> new BusinessException("User account must not be null."));
        ofNullable(user.getCard()).orElseThrow(() -> new BusinessException("User card must not be null."));

        this.validateChangeableId(user.getId(), "created");

        cardService.validateCard(user.getCard());

        if (userRepository.existsByAccountNumber(user.getAccount().getNumber())) {
            throw new BusinessException("This account number already exists.");
        }

        return userRepository.save(user);
    }

    @Transactional
    public User update(Long id, User userToUpdate) {
        this.validateChangeableId(id, "updated");
        User dbUser = findById(id);
        if (!dbUser.getId().equals(userToUpdate.getId())) {
            throw new BusinessException("Update IDs must be the same.");
        }

        dbUser.setName(userToUpdate.getName());
        dbUser.setAccount(userToUpdate.getAccount());
        dbUser.setCard(userToUpdate.getCard());
        dbUser.setFeatures(userToUpdate.getFeatures());
        dbUser.setNews(userToUpdate.getNews());

        return this.userRepository.save(dbUser);
    }

    @Transactional(readOnly = true)
    public void delete(Long id) {
        this.validateChangeableId(id, "deleted");
        User user = findById(id);
        userRepository.delete(user);
    }

    private void validateChangeableId(Long id, String operation) {
        if (UNCHANGEABLE_USER_ID.equals(id)) {
            throw new BusinessException("User with ID %d can not be %s.".formatted(UNCHANGEABLE_USER_ID, operation));
        }
    }
}
