package com.anderson.testing_deploy.core.user.usecases.port;

import java.util.UUID;

public interface DeleteUserUseCasePort {
    void execute(UUID id);
}
