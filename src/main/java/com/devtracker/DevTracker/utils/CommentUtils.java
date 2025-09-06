package com.devtracker.DevTracker.utils;

import com.devtracker.DevTracker.dto.comment.CommentGetDTO;
import com.devtracker.DevTracker.dto.details.UserDetailsDTO;
import com.devtracker.DevTracker.model.Comment;

public class CommentUtils {
    public static CommentGetDTO toComments(Comment comment){
        UserDetailsDTO user = UserDetailsUtils.toUser(comment.getAuthor());
        return new CommentGetDTO(user,comment.getCreatedAt(), comment.getId(), comment.getContent());
    }
}
