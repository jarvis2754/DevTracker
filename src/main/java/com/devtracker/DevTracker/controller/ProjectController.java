package com.devtracker.DevTracker.controller;

import com.devtracker.DevTracker.dto.project.ProjectDTO;
import com.devtracker.DevTracker.dto.project.ProjectUpdateDTO;
import com.devtracker.DevTracker.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service){
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProject(@RequestHeader("Authorization") String authHeader, @RequestBody ProjectUpdateDTO project){
        try {
            String token = authHeader.substring(7);
            service.addProjects(token,project);
            return ResponseEntity.status(HttpStatus.CREATED).body("Project created successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProjectDTO>> allProjects(){
        List<ProjectDTO> projects = service.getAllProjects();
        if(projects.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProjectDTO>> allProjectsByName(@RequestParam("keyword") String keyword){
        List<ProjectDTO> projects = service.getAllProjectsByName(keyword);
        if(projects.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(projects);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProject(@PathVariable int id, @RequestBody ProjectUpdateDTO project){
       try {
           service.updateProjectById(id, project);
           return ResponseEntity.ok("Project updated successfully");
       }catch(RuntimeException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
       }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable int id){
        boolean isDeleted = service.deleteProjectById(id);
        if(isDeleted) return ResponseEntity.status(HttpStatus.OK).body("Project deleted");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project with "+id+" doesn't exist");
    }
}
