package com.devtracker.DevTracker.mapper;

import com.devtracker.DevTracker.dto.issue.IssueDTO;
import com.devtracker.DevTracker.model.Comment;
import com.devtracker.DevTracker.model.Issue;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class IssueMapper {
    public IssueDTO toDto(Issue issue){
        Integer reporterId =issue.getReporter()!=null?issue.getReporter().getUserId():null;
        Integer assignerId=issue.getAssigner()!=null?issue.getAssigner().getUserId():null;
        Integer projectId=issue.getProject()!=null?issue.getProject().getProjectId():null;
        List<Integer> commentsIds = issue.getComments()!=null ? issue.getComments().stream().map(Comment::getId).toList():List.of();
        return  new IssueDTO(issue.getIssueTitle(),
                issue.getIssueDescription(),
                issue.getIssueType(),
                issue.getStatus(),
                issue.getPriority(),
                reporterId,
                assignerId,
                projectId,
                issue.getIssueId(),
                commentsIds,
                issue.getCreatedAt());

    }
}
