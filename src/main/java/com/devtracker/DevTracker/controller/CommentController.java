package com.devtracker.DevTracker.controller;

import com.devtracker.DevTracker.dto.comment.CommentDTO;
import com.devtracker.DevTracker.dto.comment.CommentUpdateDTO;
import com.devtracker.DevTracker.services.CommentService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<String> createComment(@RequestHeader("Authorization") String authHeader, @RequestBody CommentUpdateDTO commentDTO) {
        System.out.println(" Received Comment POST:");
        System.out.println("issueId: " + commentDTO.getIssueId());
        System.out.println("content: " + commentDTO.getContent());

        try {
            String token =authHeader.substring(7);
            CommentDTO created = commentService.createComment(token,commentDTO);
            return ResponseEntity.ok("comment created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body( e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(" Server Error: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable int id, @RequestBody CommentUpdateDTO updateDTO) {
        CommentDTO updated = commentService.updateComment(id, updateDTO);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable int id) {
        if (commentService.deleteComment(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}