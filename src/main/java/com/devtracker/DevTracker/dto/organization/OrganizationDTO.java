package com.devtracker.DevTracker.dto.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDTO {
    private int orgId;
    private String name;
    private String description;
    private Date createdAt;
    private int ownerId;
}

