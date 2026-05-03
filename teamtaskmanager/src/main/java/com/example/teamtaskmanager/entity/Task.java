package com.example.teamtaskmanager.entity;

import com.example.teamtaskmanager.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private LocalDate dueDate;


    @ManyToOne
    private Project project;


    @ManyToOne
    @JoinColumn(name = "assigned_to_id")
    private User assignedTo;
}
