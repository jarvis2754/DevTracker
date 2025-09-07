package com.devtracker.DevTracker.dto.project;

import com.devtracker.DevTracker.dto.details.UserDetailsDTO;
import com.devtracker.DevTracker.model.enums.ProjectStatus;
import lombok.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ProjectDTO extends ProjectGetDTO {
    private int projectId;
    private Date createdAt;
    private List<Integer> issueIds;
    private UserDetailsDTO createdById;
    private int orgId;

    public ProjectDTO(int projectId, String projectName, String projectDesc, Date createdAt, Date deadline, UserDetailsDTO teamLeadId, List<UserDetailsDTO> teamMemberIds, ProjectStatus status, List<Integer> issueIds , UserDetailsDTO createdById,int orgId) {
        super(projectName, projectDesc, deadline, teamLeadId, teamMemberIds, status);
        this.projectId = projectId;
        this.createdAt = createdAt;
        this.issueIds = issueIds;
        this.createdById = createdById;
        this.orgId=orgId;
    }

}
