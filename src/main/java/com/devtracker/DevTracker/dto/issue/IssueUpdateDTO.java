package com.devtracker.DevTracker.dto.issue;

import com.devtracker.DevTracker.model.enums.IssueType;
import com.devtracker.DevTracker.model.enums.Priority;
import com.devtracker.DevTracker.model.enums.Status;

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

    public String getIssueTitle() {
        return issueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        this.issueTitle = issueTitle;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getReporterId() {
        return reporterId;
    }

    public void setReporterId(Integer reporterId) {
        this.reporterId = reporterId;
    }

    public Integer getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(Integer assignerId) {
        this.assignerId = assignerId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
