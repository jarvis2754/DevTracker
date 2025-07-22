package com.devtracker.DevTracker.controller;

import com.devtracker.DevTracker.dto.user.UserDTO;
import com.devtracker.DevTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> allUsers(){
        List<UserDTO> users = service.getAllUsers();
        if(users!=null){
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(@RequestParam("keyword") String keyword){
        List<UserDTO> users = service.searchUserByIdOrName(keyword);
        if(users!=null){
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.noContent().build();

    }
}
