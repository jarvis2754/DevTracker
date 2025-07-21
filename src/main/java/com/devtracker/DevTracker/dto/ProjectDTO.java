package com.devtracker.DevTracker.dto;
import com.devtracker.DevTracker.model.enums.ProjectStatus;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private int projectId;
    private String projectName;
    private String projectDesc;
    private Date createdAt;
    private Date deadline;
    private String teamLead;
    private List<String> teamMembers;
    private ProjectStatus status;
    private int issueCount;
}
