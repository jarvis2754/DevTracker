package com.devtracker.DevTracker.controller;

import com.devtracker.DevTracker.dto.comment.CommentDTO;
import com.devtracker.DevTracker.dto.comment.CommentUpdateDTO;
import com.devtracker.DevTracker.services.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService service;

    @PostMapping("/add")
    public ResponseEntity<String> addProject(@RequestBody CommentUpdateDTO comment){
        try {
            service.addComment(comment);
            return ResponseEntity.status(HttpStatus.CREATED).body("Comment created successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CommentDTO>> allComment(){
        List<CommentDTO> comment = service.getAllComment();
        if(comment.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CommentDTO>> allCommentByName(@RequestParam("keyword") String keyword){
        List<CommentDTO> comment = service.getAllCommentByName(keyword);
        if(comment.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateComment(@PathVariable int id, @RequestBody CommentUpdateDTO comment){
        try {
            service.updateCommentById(id, comment);
            return ResponseEntity.ok("comment updated successfully");
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable int id){
        boolean isDeleted = service.deleteCommentById(id);
        if(isDeleted) return ResponseEntity.status(HttpStatus.OK).body("Comment deleted");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project with "+id+" doesn't exist");
    }


}
