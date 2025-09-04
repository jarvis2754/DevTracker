package com.devtracker.DevTracker.dto.auth;

import com.devtracker.DevTracker.model.enums.Position;
import lombok.Data;

@Data
public class SignUpRequestDTO {
    private String userName;
    private String email;
    private String uuId;
    private String password;
    private Position position;
}
