package com.devtracker.DevTracker.model;

import com.devtracker.DevTracker.model.enums.ProjectStatus;
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
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

    @Column(nullable = false)
    private String projectName;

    @Lob
    private String projectDesc;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private Date deadline;

    @ManyToOne
    @JoinColumn(name = "team_lead",foreignKey = @ForeignKey(name = "fk_project_teamLead"))
    private User teamLead;

    @ManyToOne
    @JoinColumn(name = "created_by",referencedColumnName = "userId", foreignKey = @ForeignKey(name = "fk_project_createdBy"))
    private User createdBy;

    @ManyToMany
    @JoinTable(
            name = "project_members",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            foreignKey = @ForeignKey(name = "fk_project_team_members")
    )
    private List<User> teamMembers;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "organization_id", foreignKey = @ForeignKey(name = "fk_project_org"))
    private Organization organization;


}
