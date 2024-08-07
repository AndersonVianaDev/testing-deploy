package com.anderson.testing_deploy.core.user.usecases.impl;

import com.anderson.testing_deploy.core.user.builder.UserBuilder;
import com.anderson.testing_deploy.core.user.dataprovider.UserRepository;
import com.anderson.testing_deploy.core.user.domain.User;
import com.anderson.testing_deploy.core.user.dtos.UserDTO;
import com.anderson.testing_deploy.core.exceptions.DataConflictException;
import com.anderson.testing_deploy.core.user.usecases.port.RegisterUserUseCasePort;

import static com.anderson.testing_deploy.core.exceptions.constants.ExceptionConstants.EMAIL_ALREADY_REGISTERED;

public class RegisterUserUseCaseImpl implements RegisterUserUseCasePort {

    private final UserRepository repository;

    public RegisterUserUseCaseImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User execute(UserDTO obj) {
        if (repository.findByEmail(obj.email()).isPresent()) throw new DataConflictException(EMAIL_ALREADY_REGISTERED);

        var user = new UserBuilder()
                .withName(obj.name())
                .withEmail(obj.email())
                .build();

        return repository.save(user);
    }
}
