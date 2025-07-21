package com.devtracker.DevTracker.repository;

import com.devtracker.DevTracker.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project , Integer> {
    Optional<Project> findByProjectNameContainingIgnoreCase(String projectName);
}
