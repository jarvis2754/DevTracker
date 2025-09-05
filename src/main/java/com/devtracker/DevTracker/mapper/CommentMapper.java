package com.devtracker.DevTracker.mapper;


import com.devtracker.DevTracker.dto.comment.CommentDTO;
import com.devtracker.DevTracker.dto.details.UserDetailsDTO;
import com.devtracker.DevTracker.model.Comment;
import com.devtracker.DevTracker.utils.UserDetailsUtils;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentDTO toDto(Comment comment) {
        CommentDTO dto = new CommentDTO();
        UserDetailsDTO userId = comment.getAuthor() != null ? UserDetailsUtils.toUser(comment.getAuthor()): null;
        Integer issueId = comment.getIssueId() != null ? comment.getIssueId().getId() : null;
        dto.setAuthorId(userId);
        dto.setIssueId(issueId);
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setContent(comment.getContent());
        dto.setId(comment.getId());
        return dto;
    }
}
