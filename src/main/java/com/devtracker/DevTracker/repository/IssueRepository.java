package com.devtracker.DevTracker.repository;

import com.devtracker.DevTracker.model.Issue;
import com.devtracker.DevTracker.model.enums.IssueType;
import com.devtracker.DevTracker.model.enums.Status;
import com.devtracker.DevTracker.model.enums.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {

    // Find by exact Issue ID
    Issue findByIssueId(int issueId);

    // Find all by issue type
    List<Issue> findByIssueType(IssueType issueType);

    // Find all by status
    List<Issue> findByStatus(Status status);

    // Find all by priority
    List<Issue> findByPriority(Priority priority);

    // Find issues where description contains a keyword (case-insensitive)
    List<Issue> findByIssueDescriptionContainingIgnoreCase(String keyword);

    // Find all by multiple filters (optional combo)
    List<Issue> findByIssueTypeAndStatusAndPriority(
            IssueType issueType,
            Status status,
            Priority priority
    );
}
