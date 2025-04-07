package com.example.dio.projeto.controller;

import com.example.dio.projeto.controller.dto.UserCreationDto;
import com.example.dio.projeto.controller.dto.UserDto;
import com.example.dio.projeto.domain.entity.User;
import com.example.dio.projeto.mapper.UserMapper;
import com.example.dio.projeto.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieve a list of all registered users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<List<UserDto>> listAllUsers() {
        return ResponseEntity.ok(userService.findAll()
                .stream()
                .map(UserMapper.INSTANCE::toDto)
                .toList());
    }

    @GetMapping("/{idUser}")
    @Operation(summary = "Get a user by ID", description = "Retrieve a specific user based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserDto> listUserById(@PathVariable Long idUser) {
        return ResponseEntity.ok(UserMapper.INSTANCE.toDto(userService.findById(idUser)));

    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new user and return the created user's data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided")
    })
    public ResponseEntity<UserDto> createUser(@RequestBody UserCreationDto userCreationDto) {
        User user = UserMapper.INSTANCE.toEntity(userCreationDto);
        User createdUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.INSTANCE.toDto(createdUser));
    }

    @PutMapping("/{idUser}")
    @Operation(summary = "Update a user", description = "Update the data of an existing user based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided")
    })
    public ResponseEntity<UserDto> update(@PathVariable Long idUser, @RequestBody UserCreationDto userDto) {
        User updatedUser = userService.update(idUser, UserMapper.INSTANCE.toEntity(userDto));
        return ResponseEntity.ok(UserMapper.INSTANCE.toDto(updatedUser));
    }

    @DeleteMapping("/{idUser}")
    @Operation(summary = "Delete a user", description = "Delete an existing user based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable Long idUser) {
        userService.delete(idUser);
        return ResponseEntity.noContent().build();
    }

}
