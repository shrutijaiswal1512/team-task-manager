package com.example.teamtaskmanager.service;

import com.example.teamtaskmanager.dto.AuthRequest;
import com.example.teamtaskmanager.dto.AuthResponse;

public interface AuthService {
    String register(AuthRequest request);

    AuthResponse login(AuthRequest request);
}
