package com.devtracker.DevTracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comments{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "issue_id", referencedColumnName = "id")
    private User  issue;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

//    @PrePersist
//    protected void onCreate() {
//        this.createdAt = LocalDateTime.now();
//    }

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Data CreatedAt;
}

