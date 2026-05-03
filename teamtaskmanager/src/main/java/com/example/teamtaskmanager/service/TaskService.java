package com.example.teamtaskmanager.service;

import com.example.teamtaskmanager.dto.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto createTask(TaskDto dto);

    List<TaskDto> getTasksByUser(Long userId);
    List<TaskDto> getTasksByProject(Long projectId);
    TaskDto updateStatus(Long taskId, String status, Long userId);
}
