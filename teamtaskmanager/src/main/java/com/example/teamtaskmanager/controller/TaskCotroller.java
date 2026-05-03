package com.example.teamtaskmanager.controller;

import com.example.teamtaskmanager.dto.TaskDto;
import com.example.teamtaskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor

public class TaskCotroller {
    private final TaskService taskService;

    // admin creates task
    @PostMapping

    public TaskDto createTask(@RequestBody TaskDto dto) {
        return taskService.createTask(dto);
    }

    // Member view tasks
    @GetMapping("/user/{userId}")
    public List<TaskDto> getTasks(@PathVariable Long userId) {
        return taskService.getTasksByUser(userId);
    }

    // member update status
    @PutMapping("/{taskId}/status")
    public TaskDto updateStatus(@PathVariable Long taskId,
                                @RequestParam String status,
                                @RequestParam Long userId) {
        return taskService.updateStatus(taskId, status, userId);

    }
    @GetMapping("/project/{projectId}")
    public List<TaskDto> getTasksByProject(@PathVariable Long projectId) {
        return taskService.getTasksByProject(projectId);
    }

}
