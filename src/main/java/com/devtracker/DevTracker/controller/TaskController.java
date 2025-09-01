package com.devtracker.DevTracker.controller;

import com.devtracker.DevTracker.dto.task.TaskDTO;
import com.devtracker.DevTracker.dto.task.TaskUpdateDTO;
import com.devtracker.DevTracker.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addIssue(@RequestHeader("Authorization") String authHeader, @RequestBody TaskUpdateDTO issue) {
        try {
            String token = authHeader.substring(7);
            service.addIssue(token, issue);
            return ResponseEntity.status(HttpStatus.CREATED).body("Task created successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskDTO>> allIssues() {
        List<TaskDTO> issues = service.getAllIssues();
        if (issues.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(issues);
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<TaskDTO>> allIssuesByTitle(@RequestParam("keyword") String keyword) {
//        List<TaskDTO> issues = service.getAllIssuesByTitle(keyword);
//        if (issues.isEmpty())
//            return ResponseEntity.noContent().build();
//        return ResponseEntity.ok(issues);
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateIssue(@PathVariable int id, @RequestBody TaskUpdateDTO issue) {
        try {
            service.updateIssueById(id, issue);
            return ResponseEntity.ok("Task updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteIssue(@PathVariable int id) {
        boolean isDeleted = service.deleteIssueById(id);
        if (isDeleted)
            return ResponseEntity.status(HttpStatus.OK).body("Task deleted");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task with " + id + " doesn't exist");
    }
}



