package com.example.teamtaskmanager.service;

import com.example.teamtaskmanager.dto.AuthRequest;
import com.example.teamtaskmanager.dto.AuthResponse;
import com.example.teamtaskmanager.entity.User;
import com.example.teamtaskmanager.enums.Role;
import com.example.teamtaskmanager.repository.UserRepository;
import com.example.teamtaskmanager.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl  implements AuthService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public String register(AuthRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.MEMBER);

        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user);

        return new AuthResponse(
                token,
                user.getRole().name(),
                user.getId()
        );
    }
}
