package com.devtracker.DevTracker.dto.user;

import com.devtracker.DevTracker.model.enums.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateDTO {
    private String userName;
    private String uuid;
    private String email;
    @JsonIgnore
    private String password;
    private Position position;

    public UserUpdateDTO(String userName, String email, String password, Position position,String uuid) {
        this.uuid = uuid;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.position = position;
    }
}
