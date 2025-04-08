package com.example.dio.projeto.controller;


import com.example.dio.projeto.controller.dto.CardDto;
import com.example.dio.projeto.controller.dto.UpdateLimitCardDto;
import com.example.dio.projeto.controller.dto.UserDto;
import com.example.dio.projeto.mapper.CardMapper;
import com.example.dio.projeto.mapper.UserMapper;
import com.example.dio.projeto.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    @Operation(summary = "Get all cards", description = "Retrieve a list of all registered cards")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<List<CardDto>> listAllCards() {
        return ResponseEntity.ok(cardService.findAll()
                .stream()
                .map(CardMapper.INSTANCE::toDto)
                .toList());
    }

    @GetMapping("/{idCard}")
    @Operation(summary = "Get a card by ID", description = "Retrieve a specific card based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<CardDto> listCardById(@PathVariable Long idCard) {
        return ResponseEntity.ok(CardMapper.INSTANCE.toDto(cardService.findById(idCard)));

    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get card by user ID", description = "Retrieve a card associated with the given user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Card found successfully"),
            @ApiResponse(responseCode = "404", description = "User or card not found")
    })
    public ResponseEntity<CardDto> getCardByUserId(@PathVariable Long userId) {
        var card = cardService.getCardByUserId(userId);
        return ResponseEntity.ok(CardMapper.INSTANCE.toDto(card));
    }


    @PatchMapping("/user/{userId}/limit")
    @Operation(summary = "Update card limit by user ID", description = "Update the card limit associated with the given user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Card limit updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid limit value"),
            @ApiResponse(responseCode = "404", description = "User or card not found")
    })
    public ResponseEntity<Void> updateCardLimit(
            @PathVariable Long userId,
            @RequestBody @Valid UpdateLimitCardDto request
    ) {
        cardService.updateCardLimitByUser(userId, request.limit());
        return ResponseEntity.noContent().build();
    }
}
