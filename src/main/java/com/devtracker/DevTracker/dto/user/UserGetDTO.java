package com.devtracker.DevTracker.dto.user;

import com.devtracker.DevTracker.model.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserGetDTO extends UserUpdateDTO{
    private List<Integer> assignedToIds;
    private List<Integer> reportedIssuesIds;
    private List<Integer> commentsIds;
    private List<Integer> leadingProjectsIds;
    private List<Integer> workingProjectsIds;

    public UserGetDTO(String userName, String email, String password, Position position, List<Integer> assignedToIds, List<Integer> reportedIssuesIds, List<Integer> commentsIds, List<Integer> leadingProjectsIds, List<Integer> workingProjectsIds) {
        super(userName, email, password, position);
        this.assignedToIds = assignedToIds;
        this.reportedIssuesIds = reportedIssuesIds;
        this.commentsIds = commentsIds;
        this.leadingProjectsIds = leadingProjectsIds;
        this.workingProjectsIds = workingProjectsIds;
    }
}
