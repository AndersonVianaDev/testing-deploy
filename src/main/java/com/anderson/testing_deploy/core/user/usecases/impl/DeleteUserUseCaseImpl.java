package com.anderson.testing_deploy.core.user.usecases.impl;

import com.anderson.testing_deploy.core.user.dataprovider.UserRepository;
import com.anderson.testing_deploy.core.user.usecases.port.DeleteUserUseCasePort;
import com.anderson.testing_deploy.core.user.usecases.port.FindUserByIdUseCasePort;

import java.util.UUID;

public class DeleteUserUseCaseImpl implements DeleteUserUseCasePort {

    private final UserRepository repository;

    private final FindUserByIdUseCasePort findUserById;

    public DeleteUserUseCaseImpl(UserRepository repository, FindUserByIdUseCasePort findUserById) {
        this.repository = repository;
        this.findUserById = findUserById;
    }

    @Override
    public void execute(UUID id) {
        findUserById.execute(id);
        repository.delete(id);
    }
}
