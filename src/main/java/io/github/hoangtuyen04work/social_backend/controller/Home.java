package io.github.hoangtuyen04work.social_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @GetMapping("/home")
    public String home() {
        return "Hello World";
    }
}
