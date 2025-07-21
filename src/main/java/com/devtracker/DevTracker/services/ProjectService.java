package com.devtracker.DevTracker.services;

import com.devtracker.DevTracker.dto.ProjectDTO;
import com.devtracker.DevTracker.mapper.ProjectMapper;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository repo;

    @Autowired
    private ProjectMapper mapper;

    public void addProjects(Project project){
        repo.save(project);
    }

    public List<ProjectDTO> getAllProjects(){
        return repo.findAll().stream().map(mapper::toDto).toList();
    }

    public List<ProjectDTO> getAllProjectsByName(String keyword){
        return repo.findByProjectNameContainingIgnoreCase(keyword).stream().map(mapper::toDto).toList();
    }
}
