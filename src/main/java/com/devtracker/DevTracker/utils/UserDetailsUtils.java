package com.devtracker.DevTracker.utils;

import com.devtracker.DevTracker.dto.details.UserDetailsDTO;
import com.devtracker.DevTracker.model.User;
import com.devtracker.DevTracker.model.enums.Position;

public class UserDetailsUtils {
    public static UserDetailsDTO toUser(User user){
        int userId = user.getUserId();
        String uuid = user.getUuId();
        String userName = user.getUserName();
        String email= user.getEmail();
        Position position = user.getPosition();
        return (new UserDetailsDTO(userId,uuid,userName,email,position));
    }
}
