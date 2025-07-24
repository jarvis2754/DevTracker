package com.devtracker.DevTracker.services;

import com.devtracker.DevTracker.dto.user.UserDTO;
import com.devtracker.DevTracker.dto.user.UserUpdateDTO;
import com.devtracker.DevTracker.mapper.UserMapper;
import com.devtracker.DevTracker.model.Comment;
import com.devtracker.DevTracker.model.Issue;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.model.User;
import com.devtracker.DevTracker.repository.CommentRepository;
import com.devtracker.DevTracker.repository.IssueRepository;
import com.devtracker.DevTracker.repository.ProjectRepository;
import com.devtracker.DevTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private IssueRepository issueRepo;

    @Autowired
    private ProjectRepository projectRepo;

    @Autowired
    private UserMapper mapper;

    public List<UserDTO> getAllUsers() {
        return userRepo.findAll().stream().map(mapper::toDo).toList();
    }

    public List<UserDTO> searchUserByIdOrName(String keyword) {
        List<User> users= userRepo.findByUserNameContainingIgnoreCase(keyword);
        if(keyword.matches("\\d+")){
            User newUser =userRepo.findById(Integer.parseInt(keyword)).orElse(null);
            if (newUser != null && !users.contains(newUser)) {
                users.add(newUser);
            }
        }
        return users.stream().map(mapper::toDo).toList();
    }

    public void addUsers(UserUpdateDTO user) {
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setPosition(user.getPosition());
        userRepo.save(newUser);
    }

    public boolean deleteUser(int id) {
        if(userRepo.existsById(id)){
            User user = userRepo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
            for(Project project: user.getProjects()){
                project.getTeamMembers().remove(user);
            }
            for(Project project:user.getLeadingProjects()){
                project.setTeamLead(null);
            }
            user.setProjects(null);
            userRepo.save(user);
            userRepo.delete(user);
            return true;
        }
        return false;
    }

    public void updateUserById(int id, UserUpdateDTO updateUser) {
        User user = userRepo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        if(updateUser.getUserName()!=null) user.setUserName(updateUser.getUserName());
        if(updateUser.getEmail()!=null) user.setEmail(updateUser.getEmail());
        if(updateUser.getPosition()!=null) user.setPosition(updateUser.getPosition());
        if(updateUser.getPassword()!=null) user.setPassword(updateUser.getPassword());


        userRepo.save(user);

    }
}
