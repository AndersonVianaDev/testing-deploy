package com.anderson.testing_deploy.core.user.usecases.port;

import com.anderson.testing_deploy.core.user.domain.User;
import com.anderson.testing_deploy.core.user.dtos.UserDTO;

public interface RegisterUserUseCasePort {
    User execute(UserDTO obj);
}
