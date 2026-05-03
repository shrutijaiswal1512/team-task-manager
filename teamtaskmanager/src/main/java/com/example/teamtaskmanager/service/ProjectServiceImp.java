package com.example.teamtaskmanager.service;

import com.example.teamtaskmanager.dto.ProjectDto;
import com.example.teamtaskmanager.entity.Project;
import com.example.teamtaskmanager.entity.User;
import com.example.teamtaskmanager.enums.Role;
import com.example.teamtaskmanager.mapper.ProjectMapper;
import com.example.teamtaskmanager.repository.ProjectRepository;
import com.example.teamtaskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImp implements  ProjectService{
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;


    @Override
    public ProjectDto createProject(ProjectDto dto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // role checking
        if (user.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only ADMIN can create project");
        }

        Project project = ProjectMapper.toEntity(dto);

        Project saved = projectRepository.save(project);

        return ProjectMapper.toDTO(saved);
    }

    @Override
    public ProjectDto addMembers(Long projectId, List<Long> userIds, Long adminId) {
        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        if (admin.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only ADMIN can add members");
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        List<User> members = userRepository.findAllById(userIds);

        project.setMembers(members);

        return ProjectMapper.toDTO(projectRepository.save(project));
    }

    @Override
    public List<ProjectDto> getAllProjects() {

            return projectRepository.findAll()
                    .stream()
                    .map(ProjectMapper::toDTO)
                    .toList();
        }

    @Override
    public List<ProjectDto> getProjectsByUser(Long userId) {
        return projectRepository.findByMembers_Id (userId)
                .stream()
                .map(ProjectMapper::toDTO)
                .toList();
    }
}


