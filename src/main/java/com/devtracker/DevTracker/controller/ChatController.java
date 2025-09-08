package com.devtracker.DevTracker.controller;

import com.devtracker.DevTracker.dto.message.ChatMessageDTO;
import com.devtracker.DevTracker.model.Message;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.model.User;
import com.devtracker.DevTracker.model.enums.ChatMode;
import com.devtracker.DevTracker.repository.MessageRepository;
import com.devtracker.DevTracker.repository.ProjectRepository;
import com.devtracker.DevTracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
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

    // ONE-TO-ONE
    @MessageMapping("/chat.private")
    public void sendPrivateMessage(@Payload ChatMessageDTO dto) {
        if (dto.getSenderId() == null || dto.getRecipientId() == null || dto.getContent() == null) {
            throw new IllegalArgumentException("senderId, recipientId, content are required for private messages");
        }

        User sender = userRepo.findById(dto.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("Sender not found: " + dto.getSenderId()));
        User recipient = userRepo.findById(dto.getRecipientId())
                .orElseThrow(() -> new IllegalArgumentException("Recipient not found: " + dto.getRecipientId()));

        Message msg = Message.builder()
                .content(dto.getContent())
                .sender(sender)
                .recipient(recipient)
                .timestamp(LocalDateTime.now())
                .type(ChatMode.PRIVATE)
                .build();
        messageRepo.save(msg);

        // IMPORTANT: convert recipient Integer -> String because Spring user destinations use String usernames
        messagingTemplate.convertAndSendToUser(
                Integer.toString(recipient.getUserId()),
                "/queue/messages",
                dto
        );
    }

    // PROJECT
    @MessageMapping("/chat.project")
    public void sendProjectMessage(@Payload ChatMessageDTO dto) {
        if (dto.getSenderId() == null || dto.getProjectId() == null || dto.getContent() == null) {
            throw new IllegalArgumentException("senderId, projectId, content are required for project messages");
        }

        User sender = userRepo.findById(dto.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("Sender not found: " + dto.getSenderId()));
        Project project = projectRepo.findById(dto.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found: " + dto.getProjectId()));

        Message msg = Message.builder()
                .content(dto.getContent())
                .sender(sender)
                .project(project)
                .timestamp(LocalDateTime.now())
                .type(ChatMode.PROJECT)
                .build();
        messageRepo.save(msg);

        messagingTemplate.convertAndSend("/topic/project." + project.getProjectId(), dto);
    }

    // ORG BROADCAST
    @MessageMapping("/chat.organization")
    public void sendOrganizationMessage(@Payload ChatMessageDTO dto) {
        if (dto.getSenderId() == null || dto.getContent() == null) {
            throw new IllegalArgumentException("senderId and content are required for organization messages");
        }

        User sender = userRepo.findById(dto.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("Sender not found: " + dto.getSenderId()));

        Message msg = Message.builder()
                .content(dto.getContent())
                .sender(sender)
                .timestamp(LocalDateTime.now())
                .type(ChatMode.ORGANIZATION)
                .build();
        messageRepo.save(msg);

        messagingTemplate.convertAndSend("/topic/organization", dto);
    }
}
