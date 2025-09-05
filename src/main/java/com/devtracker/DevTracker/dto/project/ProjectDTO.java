package com.devtracker.DevTracker.dto.project;

import com.devtracker.DevTracker.model.enums.ProjectStatus;
import lombok.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ProjectDTO extends ProjectUpdateDTO {
    private int projectId;
    private Date createdAt;
    private List<Integer> issueIds;
    private String createdById;

    public ProjectDTO(int projectId, String projectName, String projectDesc,Date createdAt, Date deadline, String teamLeadId, List<TeamMemberDTO> teamMemberIds, ProjectStatus status,  List<Integer> issueIds , String createdById) {
        super(projectName, projectDesc, deadline, teamLeadId, teamMemberIds, status);
        this.projectId = projectId;
        this.createdAt = createdAt;
        this.issueIds = issueIds;
        this.createdById = createdById;
    }

}
