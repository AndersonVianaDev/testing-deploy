package com.anderson.testing_deploy.core.user.dataprovider;

import com.anderson.testing_deploy.core.user.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID id);
    User update(User user);
    void delete(UUID id);
}
