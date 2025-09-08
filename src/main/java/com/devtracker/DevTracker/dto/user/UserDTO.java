package com.devtracker.DevTracker.dto.user;

import com.devtracker.DevTracker.model.enums.Position;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserDTO extends UserUpdateDTO{
    private List<Integer> assignedToIds;
    private List<Integer> reportedIssuesIds;
    private List<Integer> commentsIds;
    private List<Integer> leadingProjectsIds;
    private List<Integer> workingProjectsIds;
    private List<Integer> createdProjectsIds;
    private int userId;

    public UserDTO(String userName, String email, String password, Position position, List<Integer> assignedToIds, List<Integer> reportedIssuesIds, List<Integer> commentsIds, List<Integer> leadingProjectsIds, List<Integer> workingProjectsIds,List<Integer> createdProjectsIds, int userId, String uuid) {
        super(userName,email,password,position,uuid);
        this.assignedToIds =assignedToIds;
        this.reportedIssuesIds = reportedIssuesIds;
        this.commentsIds =commentsIds;
        this.leadingProjectsIds = leadingProjectsIds;
        this.workingProjectsIds =  workingProjectsIds;
        this.createdProjectsIds = createdProjectsIds;
        this.userId = userId;
    }
}
