package com.devtracker.DevTracker.controller;

import com.devtracker.DevTracker.dto.IssueDTO;
import com.devtracker.DevTracker.mapper.IssueMapper;
import com.devtracker.DevTracker.model.Issue;
import com.devtracker.DevTracker.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class IssueController {

    @Autowired
    private IssueRepository issueRepository;

    @GetMapping("/issues/{id}")
    public ResponseEntity<IssueDTO> getIssueById(@PathVariable int id) {
        Issue issue = issueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue not found"));

        IssueDTO dto = IssueMapper.toDto(issue);
        return ResponseEntity.ok(dto);
    }

    // You can add other endpoints like POST, PUT, DELETE here...
}
