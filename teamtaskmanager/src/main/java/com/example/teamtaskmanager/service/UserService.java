package com.example.teamtaskmanager.service;

import com.example.teamtaskmanager.dto.UserRequest;
import com.example.teamtaskmanager.entity.User;

import java.util.List;

public interface UserService {
    String createUser(UserRequest request);

    List<User> getAllUsers();
}
