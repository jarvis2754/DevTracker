package com.devtracker.DevTracker.services;

import com.devtracker.DevTracker.dto.user.UserDTO;
import com.devtracker.DevTracker.mapper.UserMapper;
import com.devtracker.DevTracker.model.User;
import com.devtracker.DevTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private UserMapper mapper;

    public List<UserDTO> getAllUsers() {
        return repo.findAll().stream().map(mapper::toDo).toList();
    }

    public List<UserDTO> searchUserByIdOrName(String keyword) {
        List<User> users= repo.findByUserNameContainingIgnoreCase(keyword);
        if(keyword.matches("\\d+")){
            User newUser =repo.findById(Integer.parseInt(keyword)).orElse(null);
            if (newUser != null && !users.contains(newUser)) {
                users.add(newUser);
            }
        }
        return users.stream().map(mapper::toDo).toList();
    }
}
