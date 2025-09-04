package com.devtracker.DevTracker.repository;

import com.devtracker.DevTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String userName);
    List<User> findByUserNameContainingIgnoreCase(String keyword);
    Optional<User> findByUuId(String uuid);
    List<User> findAllByUuIdIn(List<String> uuid);
}
