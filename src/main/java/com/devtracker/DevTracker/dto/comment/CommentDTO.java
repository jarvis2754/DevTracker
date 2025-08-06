package com.devtracker.DevTracker.dto.comment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CommentDTO extends CommentUpdateDTO {

    private Integer authorId;
    private Integer issueId;
    private Date createdAt;
    private int id;

    public CommentDTO(Integer author, Integer issue, Date createdAt, String content,int id) {
        super(content);
        this.authorId = author;
        this.issueId = issue;
        this.createdAt = createdAt;
        this.id=id;
    }
}
