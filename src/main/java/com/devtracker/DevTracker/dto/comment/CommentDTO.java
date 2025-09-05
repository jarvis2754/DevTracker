package com.devtracker.DevTracker.dto.comment;

import com.devtracker.DevTracker.dto.details.UserDetailsDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CommentDTO extends CommentUpdateDTO {

    private UserDetailsDTO authorId;
    private Date createdAt;
    private int id;

    public CommentDTO(UserDetailsDTO author, Date createdAt, String content, Integer issueId, int id) {
        super(content,issueId);
        this.authorId = author;
        this.createdAt = createdAt;
        this.id=id;
    }
}
