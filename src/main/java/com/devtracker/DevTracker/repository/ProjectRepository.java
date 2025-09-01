package com.devtracker.DevTracker.repository;

import com.devtracker.DevTracker.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project , Integer> {
    List<Project> findByProjectNameContainingIgnoreCase(String projectName);
}
