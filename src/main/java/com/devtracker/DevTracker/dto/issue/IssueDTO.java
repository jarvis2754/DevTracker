package com.devtracker.DevTracker.dto.issue;

import com.devtracker.DevTracker.model.enums.IssueType;
import com.devtracker.DevTracker.model.enums.Priority;
import com.devtracker.DevTracker.model.enums.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
public class IssueDTO extends IssueUpdateDTO{
    private int issueId;
    private List<Integer> comments;
    private Integer reporterId;
    private Date createdAt;

    public IssueDTO() {
    }

    public IssueDTO(String issueTitle, String issueDescription, IssueType issueType, Status status, Priority priority, Integer reporterId, Integer assignerId, Integer project, int issueId, List<Integer> comments, Date createdAt) {
        super(issueTitle, issueDescription, issueType, status, priority, assignerId, project);
        this.reporterId = reporterId;
        this.issueId = issueId;
        this.comments = comments;
        this.createdAt = createdAt;
    }
}
