package com.devtracker.DevTracker.dto.task;

import com.devtracker.DevTracker.model.enums.TaskType;
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
public class TaskDTO extends TaskUpdateDTO {
    private int id;
    private List<Integer> comments;
    private Integer reporterId;
    private Date createdAt;

    public TaskDTO() {
    }

    public TaskDTO(String issueTitle, String issueDescription, TaskType taskType, Status status, Priority priority, Integer reporterId, Integer assignerId, Integer project, int taskId, List<Integer> comments, Date createdAt) {
        super(issueTitle, issueDescription, taskType, status, priority, assignerId, project);
        this.reporterId = reporterId;
        this.id = taskId;
        this.comments = comments;
        this.createdAt = createdAt;
    }
}
