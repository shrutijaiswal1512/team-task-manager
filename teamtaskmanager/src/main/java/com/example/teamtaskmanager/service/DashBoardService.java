package com.example.teamtaskmanager.service;

import com.example.teamtaskmanager.dto.DashBoardDto;

public interface DashBoardService {


    DashBoardDto getDashboardByEmail(String email);
}
