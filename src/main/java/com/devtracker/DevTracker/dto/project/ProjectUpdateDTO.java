package com.devtracker.DevTracker.dto.project;

import com.devtracker.DevTracker.model.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUpdateDTO {
    private String projectName;
    private String projectDesc;
    private Date deadline;
    private String teamLeadId;
    private List<String> teamMemberIds;
    private ProjectStatus status;
}
