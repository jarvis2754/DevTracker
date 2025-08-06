package com.devtracker.DevTracker.dto.issue;

import com.devtracker.DevTracker.model.Comment;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.model.enums.IssueType;
import com.devtracker.DevTracker.model.enums.Priority;
import com.devtracker.DevTracker.model.enums.Status;

import java.util.Date;
import java.util.List;

public class IssueDTO extends IssueUpdateDTO{
    private int issueId;
    private List<Integer> comments;
    private Date createdAt;

    public IssueDTO() {
    }

    public IssueDTO(String issueTitle, String issueDescription, IssueType issueType, Status status, Priority priority, Integer reporterId, Integer assignerId, Integer project, int issueId, List<Integer> comments, Date createdAt) {
        super(issueTitle, issueDescription, issueType, status, priority, reporterId, assignerId, project);
        this.issueId = issueId;
        this.comments = comments;
        this.createdAt = createdAt;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public List<Integer> getComments() {
        return comments;
    }

    public void setComments(List<Integer> comments) {
        this.comments = comments;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
