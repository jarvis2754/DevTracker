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
    public ResponseEntity<List<ProjectDTO>> allProjects(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        List<ProjectDTO> projects = service.getAllProjects(token);
        if(projects.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProjectDTO>> allProjectsByName(@RequestHeader("Authorization") String authHeader, @RequestParam("keyword") String keyword){
        String token = authHeader.substring(7);
        try{
            List<ProjectDTO> projects = service.getAllProjectsByName(token,keyword);
            if(projects.isEmpty())
                return ResponseEntity.noContent().build();
            return ResponseEntity.ok(projects);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/search/{id}")
    public ResponseEntity<ProjectDTO> allProjectsByName(@PathVariable int id){
        ProjectDTO projects = service.getProjectById(id);
        if(projects==null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/min")
    public ResponseEntity<ProjectDTO> getProjectWithMinId(){
        try{
            ProjectDTO project = service.getMinimumOfProjectId();
            return ResponseEntity.ok(project);
        }catch (RuntimeException e){
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("/org/{orgId}")
    public ResponseEntity<List<ProjectDTO>> getProjectsByOrg(@PathVariable int orgId) {
        List<ProjectDTO> projects = service.getProjectsByOrganization(orgId);
        if (projects.isEmpty()) return ResponseEntity.noContent().build();
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
