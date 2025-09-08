package com.devtracker.DevTracker.dto.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationJoinDTO {
    private int orgId;
    private String passcode;
    private int userId;
}

