package com.devtracker.DevTracker.controller;

import com.devtracker.DevTracker.dto.ProjectDTO;
import com.devtracker.DevTracker.dto.ProjectUpdateDTO;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService service;

    @PostMapping("/add")
    public void addProject(@RequestBody ProjectUpdateDTO project){
        service.addProjects(project);
    }

    @GetMapping("/all")
    public List<ProjectDTO> allProjects(){
        return service.getAllProjects();
    }

    @GetMapping("/search")
    public List<ProjectDTO> allProjectsByName(@RequestParam("keyword") String keyword){
        return service.getAllProjectsByName(keyword);
    }

    @PutMapping("/update/{id}")
    public void updateProject(@PathVariable int id, @RequestBody ProjectUpdateDTO project){
       service.updateProjectById(id, project);
    }
}
