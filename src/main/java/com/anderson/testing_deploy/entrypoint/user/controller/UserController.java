package com.anderson.testing_deploy.entrypoint.user.controller;

import com.anderson.testing_deploy.core.user.domain.User;
import com.anderson.testing_deploy.core.user.dtos.UserDTO;
import com.anderson.testing_deploy.core.user.usecases.port.DeleteUserUseCasePort;
import com.anderson.testing_deploy.core.user.usecases.port.FindUserByIdUseCasePort;
import com.anderson.testing_deploy.core.user.usecases.port.RegisterUserUseCasePort;
import com.anderson.testing_deploy.core.user.usecases.port.UpdateUserUseCasePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private RegisterUserUseCasePort registerUser;

    @Autowired
    private FindUserByIdUseCasePort findUserById;

    @Autowired
    private UpdateUserUseCasePort updateUser;

    @Autowired
    private DeleteUserUseCasePort deleteUser;


    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Register a new user with the provided details.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request: Email already registered"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<User> post(@RequestBody UserDTO obj) {
        var user = registerUser.execute(obj);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri())
                .body(user);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieve a user by their unique ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found and returned"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<User> get(@PathVariable UUID id) {
        var user = findUserById.execute(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/put/{id}")
    @Operation(summary = "Update user details", description = "Update user information by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<User> put(@PathVariable UUID id, @RequestBody UserDTO obj) {
        var user = updateUser.execute(id, obj);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete user by ID", description = "Delete a user by their unique ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUser.execute(id);
        return ResponseEntity.noContent().build();
    }
}
