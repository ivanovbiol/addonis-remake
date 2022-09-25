package com.company.addonis.services.contracts;

import com.company.addonis.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(int id);

    Optional<?> save(User user);

    void delete(User user);

    Optional<User> block(User user);

    Optional<User> unblock(User user);
}
