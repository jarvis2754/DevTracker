package com.devtracker.DevTracker.mapper;

import com.devtracker.DevTracker.dto.CommentDTO;
import com.devtracker.DevTracker.dto.IssueDTO;
import com.devtracker.DevTracker.model.Comment;
import com.devtracker.DevTracker.model.Issue;

import java.util.List;
import java.util.stream.Collectors;

public class IssueMapper {

    public static IssueDTO toDto(Issue issue) {
        IssueDTO dto = new IssueDTO();
        dto.setIssueId(issue.getIssueId());
        dto.setIssueTitle(issue.getIssueTitle());
        dto.setIssueDescription(issue.getIssueDescription());
        dto.setIssueType(issue.getIssueType());
        dto.setStatus(issue.getStatus());
        dto.setPriority(issue.getPriority());
        dto.setReporterId(issue.getReporter().getUserId());
        dto.setAssignerId(issue.getAssigner().getUserId());
        dto.setProjectId(issue.getProject().getProjectId());
        dto.setCreatedAt(issue.getCreatedAt());

        List<CommentDTO> commentDTOs = issue.getComments().stream()
                .map(IssueMapper::toCommentDto)
                .collect(Collectors.toList());

        dto.setComments(commentDTOs);
        return dto;
    }

    public static CommentDTO toCommentDto(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setCommentId(comment.getCommentId());
        dto.setContent(comment.getContent());
        dto.setUserId(comment.getUser().getUserId());
        dto.setCreatedAt(comment.getCreatedAt());
        return dto;
    }
}
