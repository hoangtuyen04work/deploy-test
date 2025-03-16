package io.github.hoangtuyen04work.social_backend.services.impl;

import io.github.hoangtuyen04work.social_backend.dto.request.UserCreationRequest;
import io.github.hoangtuyen04work.social_backend.entities.UserEntity;
import io.github.hoangtuyen04work.social_backend.repositories.UserRepo;
import io.github.hoangtuyen04work.social_backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity createUserByUserId(UserCreationRequest userCreationRequest){
        UserEntity user = UserEntity.builder()
                .userId(userCreationRequest.getUserId())
                .password(passwordEncoder.encode(userCreationRequest.getPassword()))
                .build();
        return userRepo.save(user);
    }

    @Override
    public UserEntity createUserByEmail(UserCreationRequest userCreationRequest){
        UserEntity user = UserEntity.builder()
                .email(userCreationRequest.getEmail())
                .password(passwordEncoder.encode(userCreationRequest.getPassword()))
                .build();
        return userRepo.save(user);
    }

    @Override
    public UserEntity createUserByPhone(UserCreationRequest userCreationRequest){
        UserEntity user = UserEntity.builder()
                .phone(userCreationRequest.getPhone())
                .password(passwordEncoder.encode(userCreationRequest.getPassword()))
                .build();
        return userRepo.save(user);
    }
}
