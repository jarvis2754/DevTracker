package com.devtracker.DevTracker.dto.user;

import com.devtracker.DevTracker.model.Comment;
import com.devtracker.DevTracker.model.Issue;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.model.enums.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO extends UserUpdateDTO{
    private List<Integer> assignedToIds;
    private List<Integer> reportedIssuesIds;
    private List<Integer> commentsIds;
    private List<Integer> leadingProjectsIds;
    private List<Integer> workingProjectsIds;
    private int userId;



    public UserDTO(String userName, String email, String password, Position position, List<Integer> assignedToIds, List<Integer> reportedIssuesIds, List<Integer> commentsIds, List<Integer> leadingProjectsIds, List<Integer> workingProjectsIds, int userId) {
        super(userName,email,password,position);
        this.assignedToIds =assignedToIds;
        this.reportedIssuesIds = reportedIssuesIds;
        this.commentsIds =commentsIds;
        this.leadingProjectsIds = leadingProjectsIds;
        this.workingProjectsIds =  workingProjectsIds;
        this.userId = userId;
    }
}
