package com.devtracker.DevTracker.repository;

import com.devtracker.DevTracker.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Integer> {
    List<Issue> findByIssueTitleContainingIgnoreCase(String keyword);
}
