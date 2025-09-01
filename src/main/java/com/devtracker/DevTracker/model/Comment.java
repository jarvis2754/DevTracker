package com.devtracker.DevTracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class Comment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id" ,foreignKey = @ForeignKey(name = "fk_comment_author"))
    private User author;

    @ManyToOne
    @JoinColumn(name = "issue_id",nullable = false, foreignKey = @ForeignKey(name = "fk_comment_issue_id"))
    private Task issueId;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}

