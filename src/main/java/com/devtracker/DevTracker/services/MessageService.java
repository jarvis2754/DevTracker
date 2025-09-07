package com.devtracker.DevTracker.services;

import com.devtracker.DevTracker.dto.message.MessageDTO;
import com.devtracker.DevTracker.mapper.MessageMapper;
import com.devtracker.DevTracker.model.Message;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.model.User;
import com.devtracker.DevTracker.model.enums.ChatMode;
import com.devtracker.DevTracker.repository.MessageRepository;
import com.devtracker.DevTracker.repository.ProjectRepository;
import com.devtracker.DevTracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    @Autowired
    private MessageRepository repo;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;

    public List<MessageDTO> getPrivateMessages(Integer userId, Integer recipientId) {
        System.out.println("user"+userId+" "+recipientId);
        User user =userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));

        User recipient =userRepository.findById(recipientId).orElseThrow(()->new RuntimeException("User not found"));

        return repo.findBySenderAndRecipientOrRecipientAndSenderOrderByTimestamp(
                user, recipient, user, recipient).stream().map(MessageMapper::toDto).toList();
    }

    public List<MessageDTO> getProjectMessages(Integer projectId) {
        Project proj = projectRepository.findById(projectId).orElseThrow(()->new RuntimeException("proj not found"));
        return repo.findByProjectOrderByTimestamp(proj).stream().map(MessageMapper::toDto).toList();
    }

    public List<MessageDTO> getOrganizationMessages() {
        return repo.findByTypeOrderByTimestamp(ChatMode.ORGANIZATION).stream().map(MessageMapper::toDto).toList();
    }

    public Message saveMessage(Message message) {
        return repo.save(message);
    }
}

