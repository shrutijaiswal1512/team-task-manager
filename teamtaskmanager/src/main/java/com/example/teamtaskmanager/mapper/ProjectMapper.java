package com.example.teamtaskmanager.mapper;

import com.example.teamtaskmanager.dto.ProjectDto;
import com.example.teamtaskmanager.entity.Project;
import com.example.teamtaskmanager.entity.User;

import java.util.stream.Collectors;
import java.util.List;

public class ProjectMapper {
    // Entity -DTO
    public static ProjectDto toDTO(Project project) {

        if (project == null) return null;

        ProjectDto dto = new ProjectDto();
        dto.setId(project.getId());
        dto.setName(project.getName());

        // convert List<User> → List<Long>
        if (project.getMembers() != null) {
            List<Long> memberIds = project.getMembers()
                    .stream()
                    .map(User::getId)
                    .collect(Collectors.toList());

            dto.setMemberIds(memberIds);
        }

        return dto;
    }

    // DTO - Entity (without members)
    public static Project toEntity(ProjectDto dto) {

        if (dto == null) return null;

        Project project = new Project();
        project.setId(dto.getId());
        project.setName(dto.getName());


        return project;
    }
}
