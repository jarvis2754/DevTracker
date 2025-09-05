package com.devtracker.DevTracker.mapper;

import com.devtracker.DevTracker.dto.project.ProjectDTO;
import com.devtracker.DevTracker.dto.project.TeamMemberDTO;
import com.devtracker.DevTracker.model.Task;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.model.User;
import com.devtracker.DevTracker.model.enums.Position;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectMapper {
    public ProjectDTO toDto(Project project){
        String teamLeadId = project.getTeamLead()!=null?project.getTeamLead().getUuId():null;
        List<TeamMemberDTO> teamMemberIds =project.getTeamMembers()!=null ? project
                                .getTeamMembers().stream()
                                .map(ProjectMapper::toTeamMembers)
                                .toList():List.of();
//      int issueCount = project.getTasks()!=null ? project.getTasks().size():0;

        String createdById = project.getCreatedBy()!=null?project.getCreatedBy().getUuId():null;

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
                createdById);
    }

    public static TeamMemberDTO toTeamMembers(User user){
        int userId = user.getUserId();
        String uuid = user.getUuId();
        String userName = user.getUserName();
        String email= user.getEmail();
        Position position = user.getPosition();
        return (new TeamMemberDTO(userId,uuid,userName,email,position));
    }
}
