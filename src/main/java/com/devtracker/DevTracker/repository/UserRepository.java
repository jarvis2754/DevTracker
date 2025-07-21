package com.devtracker.DevTracker.repository;

import com.devtracker.DevTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {}
