package com.priyanshi.event_registration_platform.mapper;
import com.priyanshi.event_registration_platform.model.Role;
import com.priyanshi.event_registration_platform.dto.CreateUserRequest;
import com.priyanshi.event_registration_platform.dto.UserResponse;
import com.priyanshi.event_registration_platform.model.User;
import org.springframework.stereotype.Component;
import com.priyanshi.event_registration_platform.dto.RegisterRequest;
@Component
public class UserMapper {

    public User toEntity(CreateUserRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        user.setRole(Role.ATTENDEE);
        user.setEnabled(false);
        return user;
    }

    public User toEntity(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        user.setRole(Role.ATTENDEE);
        user.setEnabled(false);

        return user;
    }

    public UserResponse toResponse(User user) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());

        return response;
    }

}