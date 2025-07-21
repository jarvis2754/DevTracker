package com.devtracker.DevTracker.mapper;


import com.devtracker.DevTracker.dto.ProjectDTO;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectMapper {
    public ProjectDTO toDto(Project project){
        String teamLead = project.getTeamLead()!=null?project.getTeamLead().getUserName():null;
        List<String> teamMembers =project.getTeamMembers()!=null ? project
                                .getTeamMembers().stream()
                                .map(User::getUserName)
                                .toList():List.of();
        int issueCount = project.getIssues()!=null ? project.getIssues().size():0;

        return new ProjectDTO(
                project.getProjectId(),
                project.getProjectName(),
                project.getProjectDesc(),
                project.getCreatedAt(),
                project.getDeadline(),
                teamLead,
                teamMembers,
                project.getStatus(),
                issueCount);
    }
}
