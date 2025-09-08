package com.devtracker.DevTracker.mapper;

import com.devtracker.DevTracker.dto.project.ProjectDTO;
import com.devtracker.DevTracker.dto.details.UserDetailsDTO;
import com.devtracker.DevTracker.model.Task;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.utils.UserDetailsUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectMapper {
    public ProjectDTO toDto(Project project){
        UserDetailsDTO teamLeadId = project.getTeamLead()!=null? UserDetailsUtils.toUser(project.getTeamLead()):null;
        List<UserDetailsDTO> teamMemberIds =project.getTeamMembers()!=null ? project
                                .getTeamMembers().stream()
                                .map(UserDetailsUtils::toUser)
                                .toList():List.of();
//      int issueCount = project.getTasks()!=null ? project.getTasks().size():0;

        UserDetailsDTO createdById = project.getCreatedBy()!=null? UserDetailsUtils.toUser(project.getCreatedBy()):null;
        List<Integer> issueIds = project.getTasks()!=null ? project.getTasks().stream().map(Task::getId).toList():List.of();

        return new ProjectDTO(
                project.getProjectId(),
                project.getProjectName(),
                project.getProjectDesc(),
                project.getCreatedAt(),
                project.getDeadline(),
                teamLeadId,
                teamMemberIds,
                project.getStatus(),
                issueIds,
                createdById,
                project.getOrganization().getOrgId()
        );
    }

}
