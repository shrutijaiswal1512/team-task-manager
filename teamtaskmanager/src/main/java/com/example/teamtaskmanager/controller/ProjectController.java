package com.example.teamtaskmanager.controller;

import com.example.teamtaskmanager.dto.ProjectDto;
import com.example.teamtaskmanager.service.ProjectService;
import com.example.teamtaskmanager.service.ProjectServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    // ADMIN creates project
    @PostMapping
    public ProjectDto createProject(@RequestBody ProjectDto dto,
                                    @RequestParam Long userId) {
        return projectService.createProject(dto, userId);
    }

    // ADMIN adds members
    @PutMapping("/{projectId}/members")
    public ProjectDto addMembers(@PathVariable Long projectId,
                                 @RequestBody List<Long> userIds,
                                 @RequestParam Long adminId) {
        return projectService.addMembers(projectId, userIds, adminId);
    }

    @GetMapping
    public List<ProjectDto> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/user/{userId}")
    public List<ProjectDto> getProjectsByUser(@PathVariable Long userId) {
        return projectService.getProjectsByUser(userId);
    }
}
