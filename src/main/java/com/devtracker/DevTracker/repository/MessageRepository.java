package com.devtracker.DevTracker.repository;

import com.devtracker.DevTracker.model.Message;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByRecipient(User recipient);
    List<Message> findByProject(Project project);
    List<Message> findByType(String type); // for organization-wide
}

