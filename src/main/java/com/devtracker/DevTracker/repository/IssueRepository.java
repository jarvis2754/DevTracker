package com.devtracker.DevTracker.repository;

import com.devtracker.DevTracker.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue,Integer> {


}
