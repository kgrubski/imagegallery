package com.ul.imagegallery.database.repository;

import com.ul.imagegallery.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
