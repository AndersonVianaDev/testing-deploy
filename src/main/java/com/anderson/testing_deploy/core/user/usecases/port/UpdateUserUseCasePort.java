package com.anderson.testing_deploy.core.user.usecases.port;

import com.anderson.testing_deploy.core.user.domain.User;
import com.anderson.testing_deploy.core.user.dtos.UserDTO;

import java.util.UUID;

public interface UpdateUserUseCasePort {
    User execute(UUID id, UserDTO obj);
}
