package com.priyanshi.event_registration_platform.service;

import com.priyanshi.event_registration_platform.mapper.UserMapper;
import com.priyanshi.event_registration_platform.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.priyanshi.event_registration_platform.dto.CreateUserRequest;
import com.priyanshi.event_registration_platform.dto.UserResponse;
import com.priyanshi.event_registration_platform.model.User;
import com.priyanshi.event_registration_platform.exception.UserNotFoundException;
import java.util.List;
@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository,
                       UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    public UserResponse createUser(CreateUserRequest request) {

        User user = mapper.toEntity(request);

        User savedUser = repository.save(user);

        return mapper.toResponse(savedUser);
    }

    public List<UserResponse> getAllUsers() {

        List<User> users = repository.findAll();

        return users.stream()
                .map(mapper::toResponse)
                .toList();
    }
    public UserResponse getUserById(Integer id) {

        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
        return mapper.toResponse(user);
    }
    public UserResponse updateUser(Integer id, CreateUserRequest request) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id " + id));

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        User updatedUser = repository.save(user);

        return mapper.toResponse(updatedUser);
    }
    public void deleteUser(Integer id) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id " + id));

        repository.delete(user);
    }
}