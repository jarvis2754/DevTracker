package com.devtracker.DevTracker.services;

import com.devtracker.DevTracker.dto.comment.CommentDTO;
import com.devtracker.DevTracker.dto.comment.CommentUpdateDTO;
import com.devtracker.DevTracker.mapper.CommentMapper;
import com.devtracker.DevTracker.model.Comment;
import com.devtracker.DevTracker.model.Issue;
import com.devtracker.DevTracker.model.User;
import com.devtracker.DevTracker.repository.CommentRepository;
import com.devtracker.DevTracker.repository.IssueRepository;
import com.devtracker.DevTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private IssueRepository issueRepository;


    public CommentDTO createComment(CommentUpdateDTO commentDTO) {

        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());


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