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
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int issueId;

    private String issueTitle;
    private String issueDescription;

    @Enumerated(EnumType.STRING)
    private IssueType issueType;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    // Foreign key to User (reporter)
    @ManyToOne
    @JoinColumn(name = "reporter_id", referencedColumnName = "userId", foreignKey = @ForeignKey(name = "fk_issue_reporter"))
    private User reporter;

    // Foreign key to User (assigner)
    @ManyToOne
    @JoinColumn(name = "assigner_id", referencedColumnName = "userId", foreignKey = @ForeignKey(name = "fk_issue_assigner"))
    private User assigner;

    // Foreign key to Project
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "project_id", referencedColumnName = "projectId", foreignKey = @ForeignKey(name = "fk_issue_project"))
    private Project project;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
