package com.company.addonis.controllers;

import com.company.addonis.models.User;
import com.company.addonis.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    @RolesAllowed("admin")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @RolesAllowed("admin")
    public ResponseEntity<User> findById(@Valid @PathVariable int id) {
        Optional<User> user = userService.findById(id);
        return ResponseEntity.of(user);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user) {
        return ResponseEntity.of(userService.save(user));
    }

    @PutMapping
    @RolesAllowed("admin")
    public ResponseEntity<?> update(@Valid @RequestBody User user) {
        return ResponseEntity.of(userService.save(user));
    }

    @DeleteMapping
    @RolesAllowed("admin")
    public void delete(@Valid @RequestBody User user) {
        userService.delete(user);
    }

    @PutMapping("/block")
    @RolesAllowed("admin")
    public ResponseEntity<User> block(@Valid @RequestBody User user) {
        Optional<User> blockedUser = userService.block(user);
        return ResponseEntity.of(blockedUser);
    }

    @PutMapping("/unblock")
    @RolesAllowed("admin")
    public ResponseEntity<User> unblock(@Valid @RequestBody User user) {
        Optional<User> unBlockedUser = userService.unblock(user);
        return ResponseEntity.of(unBlockedUser);
    }
}