package com.devtracker.DevTracker.services;

import com.devtracker.DevTracker.dto.CommentDTO;
import com.devtracker.DevTracker.dto.IssueDTO;
import com.devtracker.DevTracker.model.Comment;
import com.devtracker.DevTracker.model.Issue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    public IssueDTO convertToDto(Issue issue) {
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

        List<CommentDTO> commentDtos = issue.getComments().stream().map(comment -> {
            CommentDTO cDto = new CommentDTO();
            cDto.setCommentId(comment.getId());
            cDto.setContent(comment.getContent());
            cDto.setUserId(comment.getAuthor().getUserId());
            cDto.setCreatedAt(comment.getCreatedAt());
            return cDto;
        }).toList();

        dto.setComments(commentDtos);
        return dto;
    }
}
