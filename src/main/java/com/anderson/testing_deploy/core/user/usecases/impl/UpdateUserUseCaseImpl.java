package com.anderson.testing_deploy.core.user.usecases.impl;

import com.anderson.testing_deploy.core.user.dataprovider.UserRepository;
import com.anderson.testing_deploy.core.user.domain.User;
import com.anderson.testing_deploy.core.user.dtos.UserDTO;
import com.anderson.testing_deploy.core.user.usecases.port.FindUserByIdUseCasePort;
import com.anderson.testing_deploy.core.user.usecases.port.UpdateUserUseCasePort;

import java.util.Objects;
import java.util.UUID;

public class UpdateUserUseCaseImpl implements UpdateUserUseCasePort {

    private final UserRepository repository;

    private final FindUserByIdUseCasePort findUserById;

    public UpdateUserUseCaseImpl(UserRepository repository, FindUserByIdUseCasePort findUserById) {
        this.repository = repository;
        this.findUserById = findUserById;
    }

    @Override
    public User execute(UUID id, UserDTO obj) {
        var user = findUserById.execute(id);

        if(Objects.nonNull(obj.name()) && !Objects.equals(user.getName(), obj.name())) {
            user.setName(obj.name());
        }
        if(Objects.nonNull(obj.email()) && !Objects.equals(user.getEmail(), obj.email())) {
            user.setEmail(obj.email());
        }

        return repository.update(user);
    }
}
