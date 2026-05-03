package com.example.teamtaskmanager.mapper;

import com.example.teamtaskmanager.dto.UserDto;
import com.example.teamtaskmanager.entity.User;
import com.example.teamtaskmanager.enums.Role;

public class UserMapper {
    // Entity -  DTO conversion
    public static UserDto toDTO(User user) {

        if (user == null) return null;

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());

        // convert enum to String
        if (user.getRole() != null) {
            dto.setRole(user.getRole().name());
        }

        return dto;
    }

    // DTO -Entity conversion
    public static User toEntity(UserDto dto) {

        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());

        // convert String to enum
        if (dto.getRole() != null) {
            user.setRole(Role.valueOf(dto.getRole().toUpperCase()));
        }

        return user;
    }
}
