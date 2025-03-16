package io.github.hoangtuyen04work.social_backend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @GetMapping("/home")
    public String home() {
        SecurityContext authentication = SecurityContextHolder.getContext();

        return "Hello World";
    }
}
