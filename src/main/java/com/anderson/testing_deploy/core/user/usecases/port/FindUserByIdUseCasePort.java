package com.anderson.testing_deploy.core.user.usecases.port;

import com.anderson.testing_deploy.core.user.domain.User;

import java.util.UUID;

public interface FindUserByIdUseCasePort {
    User execute(UUID id);
}
