package com.example.teamtaskmanager.controller;

import com.example.teamtaskmanager.dto.DashBoardDto;
import com.example.teamtaskmanager.service.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/dashboard")
public class DashBoardController {

    private final DashBoardService dashBoardService;

    @GetMapping
    public DashBoardDto getDashboard(Authentication authentication) {

        String email = authentication.getName();

        return dashBoardService.getDashboardByEmail(email);
    }
}
