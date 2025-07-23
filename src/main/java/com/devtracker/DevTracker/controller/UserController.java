package com.devtracker.DevTracker.controller;

import com.devtracker.DevTracker.dto.user.UserDTO;
import com.devtracker.DevTracker.dto.user.UserGetDTO;
import com.devtracker.DevTracker.dto.user.UserUpdateDTO;
import com.devtracker.DevTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity<String> getUser(@RequestBody UserGetDTO user){
        try{
            service.getUsers(user);
            return ResponseEntity.status(HttpStatus.OK).body("User added successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUsers(@PathVariable int id){
        boolean isDeleted = service.deleteUser(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with"+id+"now found");
    }

}
