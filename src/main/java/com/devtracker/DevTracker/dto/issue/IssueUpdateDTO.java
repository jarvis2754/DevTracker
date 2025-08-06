package com.devtracker.DevTracker.dto.issue;

import com.devtracker.DevTracker.model.enums.IssueType;
import com.devtracker.DevTracker.model.enums.Priority;
import com.devtracker.DevTracker.model.enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class IssueUpdateDTO {
    private String issueTitle;
    private String issueDescription;
    private IssueType issueType;
    private Status status;
    private Priority priority;
    private Integer reporterId;
    private Integer assignerId;
    private Integer projectId;

    public IssueUpdateDTO() {
    }

    public IssueUpdateDTO(String issueTitle, String issueDescription, IssueType issueType, Status status, Priority priority, Integer reporterId, Integer assignerId, Integer projectId) {
        this.issueTitle = issueTitle;
        this.issueDescription = issueDescription;
        this.issueType = issueType;
        this.status = status;
        this.priority = priority;
        this.reporterId = reporterId;
        this.assignerId = assignerId;
        this.projectId = projectId;
    }
}
