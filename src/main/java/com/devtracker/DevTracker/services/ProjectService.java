package com.devtracker.DevTracker.services;

import com.devtracker.DevTracker.config.JwtUtil;
import com.devtracker.DevTracker.dto.project.ProjectDTO;
import com.devtracker.DevTracker.dto.project.ProjectUpdateDTO;
import com.devtracker.DevTracker.mapper.ProjectMapper;
import com.devtracker.DevTracker.model.Organization;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.model.User;
import com.devtracker.DevTracker.repository.OrganizationRepository;
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

    private OrganizationRepository orgRepo;
    @Autowired
    public void setOrgRepo(OrganizationRepository orgRepo){
        this.orgRepo = orgRepo;
    }

    public User getUserFromToken(String token){
        String email = jwtUtil.extractUsername(token);
        return userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User with this email not found"));
    }

    public Integer getOrgIdFromToken(String token){
        return(jwtUtil.extractOrgId(token));

    }

    public void addProjects(String token, ProjectUpdateDTO projectData) {
        Project data = new Project();

        Integer orgId = jwtUtil.extractOrgId(token);

        Organization org = orgRepo.findById(orgId)
                .orElseThrow(() -> new RuntimeException("Organization not found"));
        data.setOrganization(org);

        if (projectData.getTeamLeadId() != null) {
            User teamLead = userRepo.findByUuId(projectData.getTeamLeadId()).orElse(null);
            data.setTeamLead(teamLead);
        }

        if (projectData.getTeamMemberIds() != null && !projectData.getTeamMemberIds().isEmpty()) {
            List<User> teamMembers = userRepo.findAllByUuIdIn(projectData.getTeamMemberIds());
            data.setTeamMembers(teamMembers);
        } else {
            data.setTeamMembers(new ArrayList<>());
        }

        data.setCreatedBy(getUserFromToken(token));
        data.setProjectName(projectData.getProjectName());
        data.setProjectDesc(projectData.getProjectDesc());
        data.setDeadline(projectData.getDeadline());
        data.setStatus(projectData.getStatus());

        projRepo.save(data);
    }


    public List<ProjectDTO> getAllProjects(String token){
        Integer orgId = getOrgIdFromToken(token);
        return projRepo.findByOrganizationOrgId(orgId).stream().map(mapper::toDto).toList();
    }

    public List<ProjectDTO> getProjectsByOrganization(int orgId) {
        return projRepo.findByOrganizationOrgId(orgId).stream().map(mapper::toDto).toList();
    }

    public List<ProjectDTO> getAllProjectsByName(String token, String keyword){
        Integer orgId = getOrgIdFromToken(token);
        Organization organization = orgRepo.findById(orgId).orElseThrow(()->new RuntimeException("organization not found"));
        return projRepo.findByProjectNameContainingIgnoreCaseAndOrganization(keyword,organization).stream().map(mapper::toDto).toList();
    }

    public ProjectDTO getMinimumOfProjectId() {
        return mapper.toDto(projRepo.findTopByOrderByProjectIdAsc().orElseThrow(() -> new RuntimeException("Project not found")));
    }

    public ProjectDTO getProjectById(int id){
        return projRepo.findById(id).map(mapper::toDto).orElseThrow(()->new RuntimeException("Project not found"));
    }

    public void updateProjectById(int id, ProjectUpdateDTO projectUpdate) {
        Project project = projRepo.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));

        if (projectUpdate.getProjectName() != null) project.setProjectName(projectUpdate.getProjectName());
        if (projectUpdate.getProjectDesc() != null) project.setProjectDesc(projectUpdate.getProjectDesc());
        if (projectUpdate.getDeadline() != null) project.setDeadline(projectUpdate.getDeadline());
        if (projectUpdate.getStatus() != null) project.setStatus(projectUpdate.getStatus());

        if (projectUpdate.getTeamLeadId() != null) {
            User teamLead = userRepo.findByUuId(projectUpdate.getTeamLeadId())
                    .orElseThrow(() -> new RuntimeException("Team lead not found"));
            project.setTeamLead(teamLead);
        }

        if (projectUpdate.getTeamMemberIds() != null) {
            List<User> members = userRepo.findAllByUuIdIn(projectUpdate.getTeamMemberIds());
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
