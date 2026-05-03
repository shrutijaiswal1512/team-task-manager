package com.example.teamtaskmanager.controller;

import com.example.teamtaskmanager.dto.UserRequest;
import com.example.teamtaskmanager.entity.User;
import com.example.teamtaskmanager.enums.Role;
import com.example.teamtaskmanager.repository.UserRepository;
import com.example.teamtaskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public String createUser(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

}