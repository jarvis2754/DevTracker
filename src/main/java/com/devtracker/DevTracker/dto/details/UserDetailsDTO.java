package com.devtracker.DevTracker.dto.details;

import com.devtracker.DevTracker.model.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDTO {
    private int userId;
    private String uuid;
    private String userName;
    private String email;
    private Position position;
}
