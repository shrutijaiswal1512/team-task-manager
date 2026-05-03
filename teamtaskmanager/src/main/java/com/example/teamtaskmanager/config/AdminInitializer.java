package com.example.teamtaskmanager.config;

import com.example.teamtaskmanager.enums.Role;
import com.example.teamtaskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.teamtaskmanager.entity.User;

@Component
@RequiredArgsConstructor

public class AdminInitializer {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initAdmin() {
        return args -> {

            boolean adminExists = userRepository
                    .findByEmail("admin@gmail.com")
                    .isPresent();

            if (!adminExists) {

                User admin = new User();
                admin.setName("Admin");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);

                userRepository.save(admin);

                System.out.println("Default Admin Created");
            }
        };
    }
}

