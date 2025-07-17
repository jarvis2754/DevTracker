package com.devtracker.DevTracker.model;

import com.devtracker.DevTracker.model.enums.Position;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Position Position;

}
