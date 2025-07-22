package com.devtracker.DevTracker.dto.user;

import com.devtracker.DevTracker.model.enums.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserUpdateDTO {
    private String userName;
    private String email;
    @JsonIgnore
    private String password;
    private Position position;

    public UserUpdateDTO(String userName, String email, String password, Position position) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.position = position;
    }
}
