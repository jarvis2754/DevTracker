package com.devtracker.DevTracker.mapper;

import com.devtracker.DevTracker.dto.comment.CommentGetDTO;
import com.devtracker.DevTracker.dto.task.TaskDTO;
import com.devtracker.DevTracker.dto.details.UserDetailsDTO;
import com.devtracker.DevTracker.model.Comment;
import com.devtracker.DevTracker.model.Task;
import com.devtracker.DevTracker.utils.CommentUtils;
import com.devtracker.DevTracker.utils.UserDetailsUtils;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TaskMapper {
    public TaskDTO toDto(Task task){
        UserDetailsDTO reporterId = task.getReporter()!=null? UserDetailsUtils.toUser(task.getReporter()):null;
        UserDetailsDTO assignerId= task.getAssigner()!=null? UserDetailsUtils.toUser(task.getAssigner()):null;
        Integer projectId= task.getProject()!=null? task.getProject().getProjectId():null;
        List<CommentGetDTO> commentsIds = task.getComments()!=null ? task.getComments().stream().map(CommentUtils::toComments).toList():List.of();
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
