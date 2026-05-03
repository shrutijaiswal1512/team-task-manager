package com.example.teamtaskmanager.repository;

import com.example.teamtaskmanager.entity.Task;
import com.example.teamtaskmanager.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByAssignedToId(Long userId);
    long countByAssignedToId(Long userId);

    long countByAssignedToIdAndStatus(Long userId, TaskStatus status);

    List<Task> findByProjectId(Long projectId);


}
