package com.devtracker.DevTracker.dto.message;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageDTO {
    private Integer senderId;
    private Integer recipientId;  // for one-to-one
    private Integer projectId;    // for project chat
    private String content;
}

