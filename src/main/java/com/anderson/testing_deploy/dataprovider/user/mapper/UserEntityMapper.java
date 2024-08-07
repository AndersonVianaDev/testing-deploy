package com.anderson.testing_deploy.dataprovider.user.mapper;

import com.anderson.testing_deploy.core.user.builder.UserBuilder;
import com.anderson.testing_deploy.core.user.domain.User;
import com.anderson.testing_deploy.dataprovider.user.builder.UserEntityBuilder;
import com.anderson.testing_deploy.dataprovider.user.entity.UserEntity;

import java.util.Optional;

public class UserEntityMapper {
    public static UserEntity toUserEntity(User obj) {
        return new UserEntityBuilder()
                .withName(obj.getName())
                .withEmail(obj.getEmail())
                .build();
    }

    public static User toUser(UserEntity obj) {
        return new UserBuilder()
                .withId(obj.getId())
                .withName(obj.getName())
                .withEmail(obj.getEmail())
                .build();
    }

    public static Optional<User> toOptional(Optional<UserEntity> obj) {
        return obj.map(UserEntityMapper::toUser);
    }
}
