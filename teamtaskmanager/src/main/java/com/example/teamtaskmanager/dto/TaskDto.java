package com.example.teamtaskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class TaskDto {
    private Long id;
    private String title;
    private String status;
    private LocalDate dueDate;

    private Long projectId;

    private Long assignedToId;


    private String assignedToName;

}
