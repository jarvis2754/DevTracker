package com.devtracker.DevTracker.services;

import com.devtracker.DevTracker.config.JwtUtil;
import com.devtracker.DevTracker.dto.comment.CommentDTO;
import com.devtracker.DevTracker.dto.comment.CommentUpdateDTO;
import com.devtracker.DevTracker.mapper.CommentMapper;
import com.devtracker.DevTracker.model.Comment;
import com.devtracker.DevTracker.model.Task;
import com.devtracker.DevTracker.model.User;
import com.devtracker.DevTracker.repository.CommentRepository;
import com.devtracker.DevTracker.repository.TaskRepository;
import com.devtracker.DevTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    private JwtUtil jwtUtil;
    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    public User getUserFromToken(String token){
        String email = jwtUtil.extractUsername(token);
        return userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User with this email not found"));
    }


    public CommentDTO createComment(String token,CommentUpdateDTO commentDTO) {

        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        Task issueId = taskRepository.findById(commentDTO.getIssueId()).orElseThrow(()-> new RuntimeException("Task not found"));
        comment.setIssueId(issueId);
        comment.setAuthor(getUserFromToken(token));
        Comment saved = commentRepository.save(comment);
        return commentMapper.toDto(saved);
    }

    public List<CommentDTO> getAllComments() {
        return commentRepository.findAll().stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    public CommentDTO updateComment(int id, CommentUpdateDTO updateDTO) {
        Optional<Comment> commentOpt = commentRepository.findById(id);
        if (commentOpt.isPresent()) {
            Comment comment = commentOpt.get();
            comment.setContent(updateDTO.getContent());
            Comment updated = commentRepository.save(comment);
            return commentMapper.toDto(updated);
        }
        return null;
    }


    public boolean deleteComment(int id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}