package com.company.addonis.repositories;

import com.company.addonis.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String email);

    Optional<User> findByPhoneNumber(String username);
}

