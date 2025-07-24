package com.devtracker.DevTracker.mapper;

import com.devtracker.DevTracker.dto.project.ProjectDTO;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectMapper {
    public ProjectDTO toDto(Project project){
        Integer teamLeadId = project.getTeamLead()!=null?project.getTeamLead().getUserId():null;
        List<Integer> teamMemberIds =project.getTeamMembers()!=null ? project
                                .getTeamMembers().stream()
                                .map(User::getUserId)
                                .toList():List.of();
        int issueCount = project.getIssues()!=null ? project.getIssues().size():0;

        return new ProjectDTO(
                project.getProjectId(),
                project.getProjectName(),
                project.getProjectDesc(),
                project.getCreatedAt(),
                project.getDeadline(),
                teamLeadId,
                teamMemberIds,
                project.getStatus(),
                issueCount);
    }
}
