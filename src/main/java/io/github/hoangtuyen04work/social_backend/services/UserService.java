package io.github.hoangtuyen04work.social_backend.services;

import io.github.hoangtuyen04work.social_backend.dto.request.UserCreationRequest;
import io.github.hoangtuyen04work.social_backend.entities.UserEntity;

public interface UserService {
    UserEntity createUserByUserId(UserCreationRequest userCreationRequest);

    UserEntity createUserByEmail(UserCreationRequest userCreationRequest);

    UserEntity createUserByPhone(UserCreationRequest userCreationRequest);
}
