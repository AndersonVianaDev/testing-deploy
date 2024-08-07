package com.anderson.testing_deploy.config.beans;

import com.anderson.testing_deploy.core.user.dataprovider.UserRepository;
import com.anderson.testing_deploy.core.user.domain.User;
import com.anderson.testing_deploy.core.user.dtos.UserDTO;
import com.anderson.testing_deploy.core.user.usecases.impl.DeleteUserUseCaseImpl;
import com.anderson.testing_deploy.core.user.usecases.impl.FindUserByIdUseCaseImpl;
import com.anderson.testing_deploy.core.user.usecases.impl.RegisterUserUseCaseImpl;
import com.anderson.testing_deploy.core.user.usecases.impl.UpdateUserUseCaseImpl;
import com.anderson.testing_deploy.core.user.usecases.port.DeleteUserUseCasePort;
import com.anderson.testing_deploy.core.user.usecases.port.FindUserByIdUseCasePort;
import com.anderson.testing_deploy.core.user.usecases.port.RegisterUserUseCasePort;
import com.anderson.testing_deploy.core.user.usecases.port.UpdateUserUseCasePort;
import com.anderson.testing_deploy.dataprovider.user.repositories.impl.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeans {

    @Bean
    public RegisterUserUseCasePort registerUserUseCasePort(UserRepositoryImpl repository) {
        return new RegisterUserUseCaseImpl(repository);
    }

    @Bean
    public FindUserByIdUseCasePort findUserByIdUseCasePort(UserRepositoryImpl repository) {
        return new FindUserByIdUseCaseImpl(repository);
    }

    @Bean
    public UpdateUserUseCasePort updateUserUseCasePort(UserRepositoryImpl repository, FindUserByIdUseCasePort findUserById) {
        return new UpdateUserUseCaseImpl(repository, findUserById);
    }

    @Bean
    public DeleteUserUseCasePort deleteUserUseCasePort(UserRepositoryImpl repository, FindUserByIdUseCasePort findUserById) {
        return new DeleteUserUseCaseImpl(repository, findUserById);
    }
}
