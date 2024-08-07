package com.anderson.testing_deploy.dataprovider.user.repositories.impl;

import com.anderson.testing_deploy.core.exceptions.NotFoundException;
import com.anderson.testing_deploy.core.user.dataprovider.UserRepository;
import com.anderson.testing_deploy.core.user.domain.User;
import com.anderson.testing_deploy.dataprovider.user.repositories.port.SpringUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.anderson.testing_deploy.core.exceptions.constants.ExceptionConstants.USER_NOT_FOUND;
import static com.anderson.testing_deploy.dataprovider.user.mapper.UserEntityMapper.*;

@Component
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SpringUserRepository repository;

    @Override
    public User save(User user) {
        var userEntity = toUserEntity(user);
        return toUser(repository.save(userEntity));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return toOptional(repository.findByEmail(email));
    }

    @Override
    public Optional<User> findById(UUID id) {
        return toOptional(repository.findById(id));
    }

    @Override
    public User update(User user) {
        var userEntity = repository.findById(user.getId()).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        if(!Objects.equals(user.getName(), userEntity.getName())) userEntity.setName(user.getName());
        if(!Objects.equals(user.getEmail(), userEntity.getEmail())) userEntity.setEmail(user.getEmail());

        return toUser(repository.save(userEntity));
    }

    @Override
    public void delete(UUID id) {
        var userEntity = repository.findById(id).get();
        repository.delete(userEntity);
    }
}
