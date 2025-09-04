package com.devtracker.DevTracker.dto.task;

import com.devtracker.DevTracker.model.enums.TaskType;
import com.devtracker.DevTracker.model.enums.Priority;
import com.devtracker.DevTracker.model.enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TaskUpdateDTO {
    private String title;
    private String description;
    private TaskType type;
    private Status status;
    private Priority priority;
    private String assignerId;
    private Integer projectId;

    public TaskUpdateDTO() {
    }

    public TaskUpdateDTO(String issueTitle, String issueDescription, TaskType taskType, Status status, Priority priority, String assignerId, Integer projectId) {
        this.title = issueTitle;
        this.description = issueDescription;
        this.type = taskType;
        this.status = status;
        this.priority = priority;
        this.assignerId = assignerId;
        this.projectId = projectId;
    }
}
