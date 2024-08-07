package com.anderson.testing_deploy.core.user.usecases.impl;

import com.anderson.testing_deploy.core.exceptions.NotFoundException;
import com.anderson.testing_deploy.core.user.dataprovider.UserRepository;
import com.anderson.testing_deploy.core.user.domain.User;
import com.anderson.testing_deploy.core.user.usecases.port.FindUserByIdUseCasePort;

import java.util.UUID;

import static com.anderson.testing_deploy.core.exceptions.constants.ExceptionConstants.USER_NOT_FOUND;

public class FindUserByIdUseCaseImpl implements FindUserByIdUseCasePort {

    private final UserRepository repository;

    public FindUserByIdUseCaseImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User execute(UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }
}
