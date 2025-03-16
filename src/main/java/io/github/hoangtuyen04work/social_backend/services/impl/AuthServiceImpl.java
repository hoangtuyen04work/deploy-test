package io.github.hoangtuyen04work.social_backend.services.impl;

import io.github.hoangtuyen04work.social_backend.dto.request.UserCreationRequest;
import io.github.hoangtuyen04work.social_backend.dto.response.AuthResponse;
import io.github.hoangtuyen04work.social_backend.services.AuthService;
import io.github.hoangtuyen04work.social_backend.services.UserService;
import io.github.hoangtuyen04work.social_backend.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Override
    public AuthResponse signup(UserCreationRequest userCreationRequest){
        if(userCreationRequest.getEmail() != null && )
    }


}
