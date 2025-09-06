package com.devtracker.DevTracker.dto.comment;

import com.devtracker.DevTracker.dto.details.UserDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentGetDTO {
    private UserDetailsDTO authorId;
    private Date createdAt;
    private int id;
    private String content;
}
