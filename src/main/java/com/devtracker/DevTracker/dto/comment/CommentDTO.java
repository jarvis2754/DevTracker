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
    private Date createdAt;
    private int id;

    public CommentDTO(Integer author, Date createdAt, String content,Integer issueId,int id) {
        super(content,issueId);
        this.authorId = author;
        this.createdAt = createdAt;
        this.id=id;
    }
}
