package com.devtracker.DevTracker.model;

import com.devtracker.DevTracker.model.enums.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false,unique = true)
    private String uuId;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Position position;

    @OneToMany(mappedBy="assigner")
    private List<Task> assignedTo;

    @OneToMany(mappedBy="reporter")
    private List<Task> reportedIssues;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    @OneToMany(mappedBy = "teamLead")
    private List<Project> leadingProjects;

    @OneToMany(mappedBy = "createdBy")
    private List<Project> createdProjects;

    @ManyToMany(mappedBy = "teamMembers")
    private List<Project> projects;

    @ManyToOne
    @JoinColumn(name = "organization_id", foreignKey = @ForeignKey(name = "fk_user_org"))
    private Organization organization;

}
