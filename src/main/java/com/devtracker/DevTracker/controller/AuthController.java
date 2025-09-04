package com.devtracker.DevTracker.controller;

import com.devtracker.DevTracker.config.JwtUtil;
import com.devtracker.DevTracker.dto.auth.LoginRequestDTO;
import com.devtracker.DevTracker.dto.auth.SignUpRequestDTO;
import com.devtracker.DevTracker.model.User;
import com.devtracker.DevTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> register( @RequestBody SignUpRequestDTO userDTO) {

        try {
            User existingUser = userService.findByEmailId(userDTO.getEmail());
            if (existingUser != null) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Email already in use");
            }

            User user = new User();
            user.setUserName(userDTO.getUserName());
            user.setUuId(userDTO.getUuId());
            user.setEmail(userDTO.getEmail());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setPosition(userDTO.getPosition());

            userService.saveUser(user);
            return ResponseEntity.ok("User registered successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginDTO) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );
            if (auth.isAuthenticated()) {
                User user = userService.findByEmailId(loginDTO.getEmail());
                if (user == null) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user");
                }

                String token = jwtUtil.generateToken(user.getEmail());
                return ResponseEntity.ok(Map.of("token", token));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}

