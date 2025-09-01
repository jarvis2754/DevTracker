package com.devtracker.DevTracker.services;

import com.devtracker.DevTracker.config.JwtUtil;
import com.devtracker.DevTracker.dto.project.ProjectDTO;
import com.devtracker.DevTracker.dto.project.ProjectUpdateDTO;
import com.devtracker.DevTracker.mapper.ProjectMapper;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.model.User;
import com.devtracker.DevTracker.repository.ProjectRepository;
import com.devtracker.DevTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class
ProjectService {

    private ProjectRepository projRepo;
    @Autowired
    public void setProjectRepository(ProjectRepository projRepo){
        this.projRepo = projRepo;
    }

    private ProjectMapper mapper;
    @Autowired
    public void setProjectMapper(ProjectMapper mapper){
        this.mapper = mapper;
    }

    private UserRepository userRepo;
    @Autowired
    public void setUserRepository(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    private JwtUtil jwtUtil;
    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    public User getUserFromToken(String token){
        String email = jwtUtil.extractUsername(token);
        return userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User with this email not found"));
    }

    public void addProjects(String token, ProjectUpdateDTO projectData){
        Project data = new Project();
        if(projectData.getTeamLeadId()!=null){
            User teamLead = userRepo.findById(projectData.getTeamLeadId()).orElse(null);
            data.setTeamLead(teamLead);
        }else{
            data.setTeamLead(null);
        }
        if(projectData.getTeamMemberIds()!=null && !projectData.getTeamMemberIds().isEmpty()){
            List<User> teamMembers = userRepo.findAllById(projectData.getTeamMemberIds());
            data.setTeamMembers(teamMembers);
        }else{
            data.setTeamMembers(new ArrayList<>());
        }
        data.setCreatedBy(getUserFromToken(token));
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

    public void updateProjectById(int id, ProjectUpdateDTO projectUpdate) throws RuntimeException{
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

    public boolean deleteProjectById(int id) {
        if(projRepo.existsById(id)){
            projRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
