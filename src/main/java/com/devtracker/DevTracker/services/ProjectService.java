package com.devtracker.DevTracker.services;

import com.devtracker.DevTracker.dto.ProjectDTO;
import com.devtracker.DevTracker.dto.ProjectUpdateDTO;
import com.devtracker.DevTracker.mapper.ProjectMapper;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.model.User;
import com.devtracker.DevTracker.repository.ProjectRepository;
import com.devtracker.DevTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projRepo;

    @Autowired
    private ProjectMapper mapper;

    @Autowired
    private UserRepository userRepo;

    public void addProjects(ProjectUpdateDTO projectData){
        Project data = new Project();
        User teamLead = userRepo.findById(projectData.getTeamLeadId()).orElseThrow(()->new RuntimeException("Team lead not found"));
        data.setTeamLead(teamLead);
        List<User> teamMembers = projectData
                .getTeamMemberIds().stream()
                .map(id->userRepo.findById(id).orElseThrow(()->new RuntimeException("team member not found"))).toList();
        data.setTeamMembers(teamMembers);
        data.setProjectName(projectData.getProjectName());
        data.setProjectDesc(projectData.getProjectDesc());
        data.setDeadline(projectData.getDeadline());
        data.setStatus(projectData.getStatus());
        projRepo.save(data);
    }

    public List<ProjectDTO> getAllProjects(){
        return projRepo.findAll().stream().map(mapper::toDto).toList();
    }

    public List<ProjectDTO> getAllProjectsByName(String keyword){
        return projRepo.findByProjectNameContainingIgnoreCase(keyword).stream().map(mapper::toDto).toList();
    }

    public void updateProjectById(int id, ProjectUpdateDTO projectUpdate) {
        Project project = projRepo.findById(id).orElseThrow(()->new RuntimeException("Project not found"));

        if(projectUpdate.getProjectName()!=null)project.setProjectName(projectUpdate.getProjectName());
        if(projectUpdate.getProjectDesc()!=null)project.setProjectDesc(projectUpdate.getProjectDesc());
        if(projectUpdate.getDeadline()!=null)project.setDeadline(projectUpdate.getDeadline());
        if(projectUpdate.getStatus()!=null)project.setStatus(projectUpdate.getStatus());
        if(projectUpdate.getTeamLeadId()!=null){
            User teamLead = userRepo.findById(projectUpdate.getTeamLeadId()).orElseThrow(() -> new RuntimeException("Team lead not found"));
            project.setTeamLead(teamLead);
        }
        if (projectUpdate.getTeamMemberIds() != null) {
            List<User> members = userRepo.findAllById(projectUpdate.getTeamMemberIds());
            project.setTeamMembers(members);
        }
        projRepo.save(project);

    }
}
