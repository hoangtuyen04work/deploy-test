package io.github.hoangtuyen04work.social_backend.services.impl;

import io.github.hoangtuyen04work.social_backend.dto.request.UserCreationRequest;
import io.github.hoangtuyen04work.social_backend.dto.request.UserLoginRequest;
import io.github.hoangtuyen04work.social_backend.entities.UserEntity;
import io.github.hoangtuyen04work.social_backend.exception.AppException;
import io.github.hoangtuyen04work.social_backend.exception.ErrorCode;
import io.github.hoangtuyen04work.social_backend.repositories.UserRepo;
import io.github.hoangtuyen04work.social_backend.services.RoleService;
import io.github.hoangtuyen04work.social_backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity findUserById(String id) throws AppException {
        return userRepo.findById(id).orElseThrow(() -> new AppException(ErrorCode.CONFLICT));
    }

    @Override
    public UserEntity loginByEmail(UserLoginRequest request) throws AppException {
        UserEntity user =  userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_AUTHENTICATION));
        if(passwordEncoder.matches(request.getPassword(), user.getPassword())) return user;

        throw new AppException(ErrorCode.NOT_AUTHENTICATION);
    }

    @Override
    public UserEntity loginByPhone(UserLoginRequest request) throws AppException {
        UserEntity user =  userRepo.findByPhone(request.getPhone())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_AUTHENTICATION));
        if(passwordEncoder.matches(request.getPassword(), user.getPassword())) return user;
        throw new AppException(ErrorCode.NOT_AUTHENTICATION);
    }

    @Override
    public UserEntity loginByCustomId(UserLoginRequest request) throws AppException {
        UserEntity user = userRepo.findByCustomId(request.getCustomId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_AUTHENTICATION));
        if(passwordEncoder.matches(request.getPassword(), user.getPassword())) return user;
        throw new AppException(ErrorCode.NOT_AUTHENTICATION);
    }

    @Override
    public boolean existByEmail(String email){
        return userRepo.existsByEmail(email);
    }

    @Override
    public boolean existByPhone(String phone){
        return userRepo.existsByPhone(phone);
    }

    @Override
    public boolean existByCustomId(String userId){
        return userRepo.existsByCustomId(userId);
    }

    @Override
    public UserEntity createUserByCustomId(UserCreationRequest userCreationRequest) throws AppException {
        UserEntity user = UserEntity.builder()
                .customId(userCreationRequest.getCustomId())
                .userName(userCreationRequest.getUserName())
                .roles(Set.of(roleService.getRoleByRoleName("USER")))
                .password(passwordEncoder.encode(userCreationRequest.getPassword()))
                .build();
        return userRepo.save(user);
    }

    @Override
    public UserEntity createUserByEmail(UserCreationRequest userCreationRequest) throws AppException {
        UserEntity user = UserEntity.builder()
                .email(userCreationRequest.getEmail())
                .roles(Set.of(roleService.getRoleByRoleName("USER")))
                .customId(userCreationRequest.getCustomId())
                .userName(userCreationRequest.getUserName())
                .password(passwordEncoder.encode(userCreationRequest.getPassword()))
                .build();
        return userRepo.save(user);
    }

    @Override
    public boolean checkAttribute(UserCreationRequest request)  {
        return request.getCustomId() != null && !(request.getPassword() == null | request.getUserName() == null);
    }

    @Override
    public UserEntity createUserByPhone(UserCreationRequest userCreationRequest) throws AppException {
        UserEntity user = UserEntity.builder()
                .phone(userCreationRequest.getPhone())
                .customId(userCreationRequest.getCustomId())
                .userName(userCreationRequest.getUserName())
                .roles(Set.of(roleService.getRoleByRoleName("USER")))
                .password(passwordEncoder.encode(userCreationRequest.getPassword()))
                .build();
        return userRepo.save(user);
    }
}
