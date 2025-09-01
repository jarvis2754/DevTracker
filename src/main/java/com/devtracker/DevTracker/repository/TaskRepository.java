package com.devtracker.DevTracker.repository;

import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByTitleContainingIgnoreCase(String keyword);
    List<Task> findByProject(Project project);
}
