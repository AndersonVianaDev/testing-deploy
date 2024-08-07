package com.anderson.testing_deploy.entrypoint.user.controller;

import com.anderson.testing_deploy.core.user.domain.User;
import com.anderson.testing_deploy.core.user.dtos.UserDTO;
import com.anderson.testing_deploy.core.user.usecases.port.DeleteUserUseCasePort;
import com.anderson.testing_deploy.core.user.usecases.port.FindUserByIdUseCasePort;
import com.anderson.testing_deploy.core.user.usecases.port.RegisterUserUseCasePort;
import com.anderson.testing_deploy.core.user.usecases.port.UpdateUserUseCasePort;
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
    public ResponseEntity<User> post(@RequestBody UserDTO obj) {
        var user = registerUser.execute(obj);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri())
                .body(user);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> get(@PathVariable UUID id) {
        var user = findUserById.execute(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<User> put(@PathVariable UUID id, @RequestBody UserDTO obj) {
        var user = updateUser.execute(id, obj);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUser.execute(id);
        return ResponseEntity.noContent().build();
    }
}
