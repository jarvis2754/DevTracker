package com.devtracker.DevTracker.model;

import com.devtracker.DevTracker.model.enums.TaskType;
import com.devtracker.DevTracker.model.enums.Priority;
import com.devtracker.DevTracker.model.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //no

    @Column(nullable = false)
    private String title;

    @Lob
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    // Foreign key to User (reporter)
    @ManyToOne
    @JoinColumn(name = "reporter_id", referencedColumnName = "userId", foreignKey = @ForeignKey(name = "fk_task_reporter"))
    private User reporter;

    // Foreign key to User (assigner)
    @ManyToOne
    @JoinColumn(name = "assigner_id", referencedColumnName = "userId", foreignKey = @ForeignKey(name = "fk_task_assigner"))
    private User assigner;

    // Foreign key to Project
    @ManyToOne
    @JoinColumn(name = "projectId",nullable = false, referencedColumnName = "projectId", foreignKey = @ForeignKey(name = "fk_task_project"))
    private Project project;

    @OneToMany(mappedBy = "issueId",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments; //no

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt; //no
}
