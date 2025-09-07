package com.devtracker.DevTracker.model;

import com.devtracker.DevTracker.model.enums.ChatMode;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime timestamp;

    // Sender
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    // Recipient (nullable if it's project/org chat)
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;

    // Project (nullable if not project chat)
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    // Type: ONE_TO_ONE, PROJECT, ORGANIZATION
    private ChatMode type;
}
