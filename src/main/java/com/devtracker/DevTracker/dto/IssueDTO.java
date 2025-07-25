package com.devtracker.DevTracker.dto;

import com.devtracker.DevTracker.model.enums.IssueType;
import com.devtracker.DevTracker.model.enums.Priority;
import com.devtracker.DevTracker.model.enums.Status;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class IssueDTO {
    private int issueId;
    private String issueTitle;
    private String issueDescription;
    private IssueType issueType;
    private Status status;
    private Priority priority;

    private int reporterId;
    private int assignerId;
    private int projectId;

    private Date createdAt;

    private List<CommentDTO> comments;
}
