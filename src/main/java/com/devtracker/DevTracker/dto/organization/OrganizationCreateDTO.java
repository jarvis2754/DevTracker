package com.devtracker.DevTracker.dto.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationCreateDTO {
    private String name;
    private String description;
    private int ownerId; // reference to User
}

