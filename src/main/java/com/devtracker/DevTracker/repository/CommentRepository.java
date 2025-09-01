package com.devtracker.DevTracker.repository;

import com.devtracker.DevTracker.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
