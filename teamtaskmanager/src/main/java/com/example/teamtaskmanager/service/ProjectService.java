package com.example.teamtaskmanager.service;

import com.example.teamtaskmanager.dto.ProjectDto;
import java.util.List;

public interface ProjectService {
    ProjectDto createProject(ProjectDto dto, Long userId);

    ProjectDto addMembers(Long projectId, List<Long> userIds, Long adminId);

    List<ProjectDto> getAllProjects();

    List<ProjectDto> getProjectsByUser(Long userId);
}
