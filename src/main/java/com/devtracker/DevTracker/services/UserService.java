package com.devtracker.DevTracker.services;

import com.devtracker.DevTracker.dto.user.UserDTO;
import com.devtracker.DevTracker.dto.user.UserUpdateDTO;
import com.devtracker.DevTracker.mapper.UserMapper;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.model.User;
import com.devtracker.DevTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    private UserMapper mapper;
    @Autowired
    public void setUserMapper(UserMapper mapper){
        this.mapper=mapper;
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

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

    public UserDTO getUserByUUID(String uuid){
        return mapper.toDo(userRepo.findByUuId(uuid).orElseThrow(()->new RuntimeException("user with user id not found")));
    }

    public void addUsers(UserUpdateDTO user) {
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setUuId(user.getUuid());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
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
        if(updateUser.getUuid()!=null) user.setUuId(updateUser.getUuid());
        if(updateUser.getEmail()!=null) user.setEmail(updateUser.getEmail());
        if(updateUser.getPosition()!=null) user.setPosition(updateUser.getPosition());
        if(updateUser.getPassword()!=null) user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        userRepo.save(user);
    }

    public User findByEmailId(String email){
        return userRepo.findByEmail(email).orElse(null);
    }
}
