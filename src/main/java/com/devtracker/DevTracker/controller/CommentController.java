package com.devtracker.DevTracker.controller;

import com.devtracker.DevTracker.dto.comment.CommentDTO;
import com.devtracker.DevTracker.dto.comment.CommentUpdateDTO;
import com.devtracker.DevTracker.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "*") //
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping("/add")
    public ResponseEntity<?> createComment(@RequestBody CommentDTO commentDTO) {
        System.out.println(" Received Comment POST:");
        System.out.println("authorId: " + commentDTO.getAuthorId());
        System.out.println("issueId: " + commentDTO.getIssueId());
        System.out.println("content: " + commentDTO.getContent());

        try {
            CommentDTO created = commentService.createComment(commentDTO);
            return ResponseEntity.ok(created);
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






    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable int id, @RequestBody CommentUpdateDTO updateDTO) {
        CommentDTO updated = commentService.updateComment(id, updateDTO);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable int id) {
        if (commentService.deleteComment(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}