package com.example.teamtaskmanager.service;

import com.example.teamtaskmanager.dto.UserRequest;
import com.example.teamtaskmanager.entity.User;
import com.example.teamtaskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public String createUser(UserRequest request) {
        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        try {
            userRepository.save(user);
            System.out.println("User saved");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "User created successfully";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
