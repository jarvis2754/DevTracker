package com.devtracker.DevTracker.dto.task;

import com.devtracker.DevTracker.dto.comment.CommentGetDTO;
import com.devtracker.DevTracker.dto.details.UserDetailsDTO;
import com.devtracker.DevTracker.model.enums.TaskType;
import com.devtracker.DevTracker.model.enums.Priority;
import com.devtracker.DevTracker.model.enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TaskGetDTO {
    private String title;
    private String description;
    private TaskType type;
    private Status status;
    private Priority priority;
    private UserDetailsDTO assignerId;
    private Integer projectId;

    public TaskGetDTO() {
    }

    public TaskGetDTO(String issueTitle, String issueDescription, TaskType taskType, Status status, Priority priority, UserDetailsDTO assignerId, Integer projectId) {
        this.title = issueTitle;
        this.description = issueDescription;
        this.type = taskType;
        this.status = status;
        this.priority = priority;
        this.assignerId = assignerId;
        this.projectId = projectId;
    }
}
