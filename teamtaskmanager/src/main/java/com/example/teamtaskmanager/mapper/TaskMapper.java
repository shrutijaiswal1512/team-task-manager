package com.example.teamtaskmanager.mapper;

import com.example.teamtaskmanager.dto.TaskDto;
import com.example.teamtaskmanager.entity.Task;
import com.example.teamtaskmanager.enums.TaskStatus;

public class TaskMapper {
    public static Task toEntity(TaskDto dto) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        if (dto.getStatus() != null) {
            task.setStatus(TaskStatus.valueOf(dto.getStatus()));
        } else {
            task.setStatus(TaskStatus.TODO);
        }
        task.setDueDate(dto.getDueDate());
        return task;
    }

    public static TaskDto toDTO(Task task) {
        TaskDto dto = new TaskDto();

        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setStatus(task.getStatus().name());
        dto.setDueDate(task.getDueDate());

        if (task.getProject() != null) {
            dto.setProjectId(task.getProject().getId());
        }

        if (task.getAssignedTo() != null) {
            dto.setAssignedToId(task.getAssignedTo().getId());
            dto.setAssignedToName(task.getAssignedTo().getName());
        } else {
            dto.setAssignedToId(null);
            dto.setAssignedToName("Unassigned");
        }

        return dto;
    }
}
