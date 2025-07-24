package com.devtracker.DevTracker.repository;

import com.devtracker.DevTracker.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment findByCommentId(Integer commentId);
    List<Comment> findAllByCommentId(Integer commentId);
}
