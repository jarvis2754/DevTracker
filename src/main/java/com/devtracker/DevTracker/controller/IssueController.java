package com.devtracker.DevTracker.controller;

import com.devtracker.DevTracker.dto.issue.IssueDTO;
import com.devtracker.DevTracker.dto.issue.IssueUpdateDTO;
import com.devtracker.DevTracker.services.IssueService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issue")
public class IssueController {

    private final IssueService service;

    public IssueController(IssueService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addIssue(@RequestHeader("Authorization") String authHeader, @RequestBody IssueUpdateDTO issue) {
        try {
            String token = authHeader.substring(7);
            service.addIssue(token, issue);
            return ResponseEntity.status(HttpStatus.CREATED).body("Issue created successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<IssueDTO>> allIssues() {
        List<IssueDTO> issues = service.getAllIssues();
        if (issues.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(issues);
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<IssueDTO>> allIssuesByTitle(@RequestParam("keyword") String keyword) {
//        List<IssueDTO> issues = service.getAllIssuesByTitle(keyword);
//        if (issues.isEmpty())
//            return ResponseEntity.noContent().build();
//        return ResponseEntity.ok(issues);
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateIssue(@PathVariable int id, @RequestBody IssueUpdateDTO issue) {
        try {
            service.updateIssueById(id, issue);
            return ResponseEntity.ok("Issue updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteIssue(@PathVariable int id) {
        boolean isDeleted = service.deleteIssueById(id);
        if (isDeleted)
            return ResponseEntity.status(HttpStatus.OK).body("Issue deleted");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Issue with " + id + " doesn't exist");
    }
}



