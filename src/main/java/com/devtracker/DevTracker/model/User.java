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
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int userId;
    private String userName;
    @Column(unique = true, nullable = false)
    private String email;
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    private Position position;

    @OneToMany(mappedBy="assigner")
    private List<Issue> assignedTo;

    @OneToMany(mappedBy="reporter")
    private List<Issue> reportedIssues;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    @OneToMany(mappedBy = "teamLead")
    private List<Project> leadingProjects;

    @ManyToMany(mappedBy = "teamMembers")
    private List<Project> projects;

}
