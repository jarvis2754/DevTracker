package com.devtracker.DevTracker.dto.project;
import com.devtracker.DevTracker.model.enums.ProjectStatus;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ProjectDTO extends ProjectUpdateDTO {
    private int projectId;
    private Date createdAt;
    private int issueCount;

    public ProjectDTO(int projectId, String projectName, String projectDesc,Date createdAt, Date deadline, Integer teamLeadId, List<Integer> teamMemberIds, ProjectStatus status,  int issueCount) {
        super(projectName, projectDesc, deadline, teamLeadId, teamMemberIds, status);
        this.projectId = projectId;
        this.createdAt = createdAt;
        this.issueCount = issueCount;
    }

}
