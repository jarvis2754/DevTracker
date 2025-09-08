package com.devtracker.DevTracker.mapper;

import com.devtracker.DevTracker.dto.message.MessageDTO;
import com.devtracker.DevTracker.model.Message;

public class MessageMapper {
    public static MessageDTO toDto(Message message){
        Integer sender = (message.getSender()!=null)?message.getSender().getUserId():null;
        Integer receiver =(message.getRecipient()!=null)?message.getRecipient().getUserId():null;
        Integer project =(message.getProject()!=null)?message.getProject().getProjectId():null;
        return new MessageDTO(
                message.getId(),
                message.getContent(),
                message.getTimestamp(),
                sender,
                receiver,
                project,
                message.getType()
        );
    }
}
