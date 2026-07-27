package com.priyanshi.event_registration_platform.controller;

import com.priyanshi.event_registration_platform.dto.CreateUserRequest;
import com.priyanshi.event_registration_platform.dto.UserResponse;
import com.priyanshi.event_registration_platform.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        return service.createUser(request);
    }
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Integer id) {
        return service.getUserById(id);
    }
    @PutMapping("/{id}")
    public UserResponse updateUser(
            @PathVariable Integer id,
            @RequestBody CreateUserRequest request) {

        return service.updateUser(id, request);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {

        service.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}