package com.devtracker.DevTracker.dto.comment;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentUpdateDTO {
    private String content;
    private Integer issueId;
}
