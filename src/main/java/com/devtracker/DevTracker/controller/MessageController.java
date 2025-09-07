package com.devtracker.DevTracker.controller;

import com.devtracker.DevTracker.dto.message.MessageDTO;
import com.devtracker.DevTracker.model.Message;
import com.devtracker.DevTracker.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/private/{userId}/{recipientId}")
    public List<MessageDTO> getPrivateMessages(@PathVariable Integer userId,
                                               @PathVariable Integer recipientId) {
        return messageService.getPrivateMessages(userId, recipientId);
    }

    @GetMapping("/project/{projectId}")
    public List<MessageDTO> getProjectMessages(@PathVariable Integer projectId) {
        return messageService.getProjectMessages(projectId);
    }

    @GetMapping("/organization")
    public List<MessageDTO> getOrganizationMessages() {
        return messageService.getOrganizationMessages();
    }
}
