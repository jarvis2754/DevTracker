package com.devtracker.DevTracker.repository;

import com.devtracker.DevTracker.model.Message;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.model.User;
import com.devtracker.DevTracker.model.enums.ChatMode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByRecipient(User recipient);
    List<Message> findByProject(Project project);
    List<Message> findByType(ChatMode type);
    List<Message> findBySenderAndRecipientOrRecipientAndSenderOrderByTimestamp(
            User senderId, User recipientId,
            User recipientId2, User senderId2
    );
    // project chat
    List<Message> findByProjectOrderByTimestamp(Project projectId);

    List<Message> findByTypeOrderByTimestamp(ChatMode mode);
}

