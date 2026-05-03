package com.example.teamtaskmanager.service;

import com.example.teamtaskmanager.dto.TaskDto;
import com.example.teamtaskmanager.entity.Project;
import com.example.teamtaskmanager.entity.User;
import com.example.teamtaskmanager.enums.TaskStatus;
import com.example.teamtaskmanager.mapper.TaskMapper;
import com.example.teamtaskmanager.repository.ProjectRepository;
import com.example.teamtaskmanager.repository.TaskRepository;
import com.example.teamtaskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.teamtaskmanager.enums.Role;
import com.example.teamtaskmanager.entity.Task;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService{
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;


    @Override
    public TaskDto createTask(TaskDto dto) {
        //  Get logged-in user from JWT
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Role check
        if (user.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only ADMIN can create tasks");
        }

        Task task = TaskMapper.toEntity(dto);
        task.setStatus(TaskStatus.TODO);


        User assigned = userRepository.findById(dto.getAssignedToId())
                .orElseThrow(() -> new RuntimeException("Assigned user not found"));

        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        task.setAssignedTo(assigned);
        task.setProject(project);
        task.setStatus(TaskStatus.TODO); // default

        return TaskMapper.toDTO(taskRepository.save(task));
    }
    @Override
    public List<TaskDto> getTasksByUser(Long userId) {
        List<Task> tasks = taskRepository.findByAssignedToId(userId);

        return tasks.stream()
                .map(TaskMapper::toDTO)
                .toList();
    }

    @Override
    public List<TaskDto> getTasksByProject(Long projectId) {
        List<Task> tasks = taskRepository.findByProjectId(projectId);

        return tasks.stream()
                .map(TaskMapper::toDTO)
                .toList();
    }

    @Override
    public TaskDto updateStatus(Long taskId, String status, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getAssignedTo().getId().equals(userId)) {
            throw new RuntimeException("Not allowed");
        }

        task.setStatus(TaskStatus.valueOf(status));

        return TaskMapper.toDTO(taskRepository.save(task));
    }
}
