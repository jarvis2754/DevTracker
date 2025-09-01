package com.devtracker.DevTracker.controller;

import com.devtracker.DevTracker.dto.message.ChatMessageDTO;
import com.devtracker.DevTracker.model.Message;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.model.User;
import com.devtracker.DevTracker.repository.MessageRepository;
import com.devtracker.DevTracker.repository.ProjectRepository;
import com.devtracker.DevTracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageRepository messageRepo;
    private final UserRepository userRepo;
    private final ProjectRepository projectRepo;

    // ✅ One-to-One Chat
    @MessageMapping("/chat.private")
    public void sendPrivateMessage(@Payload ChatMessageDTO dto) {
        User sender = userRepo.findById(dto.getSenderId()).orElseThrow();
        User recipient = userRepo.findById(dto.getRecipientId()).orElseThrow();

        Message msg = Message.builder()
                .content(dto.getContent())
                .sender(sender)
                .recipient(recipient)
                .timestamp(LocalDateTime.now())
                .type("ONE_TO_ONE")
                .build();
        messageRepo.save(msg);

        messagingTemplate.convertAndSendToUser(Integer.toString(recipient.getUserId()), "/queue/messages", dto);
    }

    // ✅ Project Chat
    @MessageMapping("/chat.project")
    public void sendProjectMessage(@Payload ChatMessageDTO dto) {
        User sender = userRepo.findById(dto.getSenderId()).orElseThrow();
        Project project = projectRepo.findById(dto.getProjectId()).orElseThrow();

        Message msg = Message.builder()
                .content(dto.getContent())
                .sender(sender)
                .project(project)
                .timestamp(LocalDateTime.now())
                .type("PROJECT")
                .build();
        messageRepo.save(msg);

        messagingTemplate.convertAndSend("/topic/project." + project.getProjectId(), dto);
    }

    // ✅ Organization Chat (broadcast to all users)
    @MessageMapping("/chat.organization")
    public void sendOrganizationMessage(@Payload ChatMessageDTO dto) {
        User sender = userRepo.findById(dto.getSenderId()).orElseThrow();

        Message msg = Message.builder()
                .content(dto.getContent())
                .sender(sender)
                .timestamp(LocalDateTime.now())
                .type("ORGANIZATION")
                .build();
        messageRepo.save(msg);

        messagingTemplate.convertAndSend("/topic/organization", dto);
    }
}

