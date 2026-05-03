package com.example.teamtaskmanager.controller;

import com.example.teamtaskmanager.dto.AuthRequest;
import com.example.teamtaskmanager.dto.AuthResponse;
import com.example.teamtaskmanager.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    //signup/register
    @PostMapping("/register")
    public String register( @Valid @RequestBody AuthRequest request) {
        return authService.register(request);
    }

    //  Login
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }
}
