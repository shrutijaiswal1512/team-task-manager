package com.example.teamtaskmanager.service;

import com.example.teamtaskmanager.dto.DashBoardDto;
import com.example.teamtaskmanager.entity.Task;
import com.example.teamtaskmanager.entity.User;
import com.example.teamtaskmanager.repository.TaskRepository;
import com.example.teamtaskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashBoardServiceImpl implements  DashBoardService{
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    @Override
    public DashBoardDto getDashboardByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Task> tasks = taskRepository.findByAssignedToId(user.getId());

        DashBoardDto dto = new DashBoardDto();

        dto.setTotalTasks(tasks.size());

        dto.setCompletedTasks(tasks.stream()
                .filter(t -> t.getStatus().name().equals("DONE")).count());

        dto.setPendingTasks(tasks.stream()
                .filter(t -> t.getStatus().name().equals("TODO")).count());

        dto.setInProgressTasks(tasks.stream()
                .filter(t -> t.getStatus().name().equals("IN_PROGRESS")).count());
        dto.setOverdueTasks(
                tasks.stream()
                        .filter(t ->
                                t.getDueDate() != null &&
                                        t.getDueDate().isBefore(java.time.LocalDate.now()) &&
                                        !t.getStatus().name().equals("DONE")
                        )
                        .count()
        );

        return dto;


    }
}
