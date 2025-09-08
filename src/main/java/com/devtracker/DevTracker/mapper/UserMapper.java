package com.devtracker.DevTracker.mapper;

import com.devtracker.DevTracker.dto.user.UserDTO;
import com.devtracker.DevTracker.model.Comment;
import com.devtracker.DevTracker.model.Task;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public UserDTO toDo(User user){
        List<Integer> assignedToIds = user.getAssignedTo()!=null ? user.getAssignedTo().stream().map(Task::getId).toList():List.of();
        List<Integer> reportedIssuesIds = user.getReportedIssues()!=null ? user.getReportedIssues().stream().map(Task::getId).toList():List.of();
        List<Integer> commentsIds =user.getComments()!=null ? user.getComments().stream().map(Comment::getId).toList() : List.of();
        List<Integer> leadingProjectsIds = user.getLeadingProjects()!=null ? user.getLeadingProjects().stream().map(Project::getProjectId).toList() : List.of();
        List<Integer> workingProjectsIds = user.getProjects()!=null ? user.getProjects().stream().map(Project::getProjectId).toList() : List.of();
        List<Integer> createdProjectsIds = user.getCreatedProjects()!=null ? user.getCreatedProjects().stream().map(Project::getProjectId).toList() : List.of();

        return new UserDTO(user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                user.getPosition(),
                assignedToIds,
                reportedIssuesIds,
                commentsIds,
                leadingProjectsIds,
                workingProjectsIds,
                createdProjectsIds,
                user.getUserId(),
                user.getUuId());

    }
}
