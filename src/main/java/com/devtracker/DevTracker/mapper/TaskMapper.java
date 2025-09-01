package com.devtracker.DevTracker.mapper;

import com.devtracker.DevTracker.dto.task.TaskDTO;
import com.devtracker.DevTracker.model.Comment;
import com.devtracker.DevTracker.model.Task;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TaskMapper {
    public TaskDTO toDto(Task task){
        Integer reporterId = task.getReporter()!=null? task.getReporter().getUserId():null;
        Integer assignerId= task.getAssigner()!=null? task.getAssigner().getUserId():null;
        Integer projectId= task.getProject()!=null? task.getProject().getProjectId():null;
        List<Integer> commentsIds = task.getComments()!=null ? task.getComments().stream().map(Comment::getId).toList():List.of();
        return  new TaskDTO(task.getTitle(),
                task.getDescription(),
                task.getType(),
                task.getStatus(),
                task.getPriority(),
                reporterId,
                assignerId,
                projectId,
                task.getId(),
                commentsIds,
                task.getCreatedAt());

    }
}
