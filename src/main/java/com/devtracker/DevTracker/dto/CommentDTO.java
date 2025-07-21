package com.devtracker.DevTracker.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {
    private int commentId;
    private String content;
    private int userId;
    private Date createdAt;
}
