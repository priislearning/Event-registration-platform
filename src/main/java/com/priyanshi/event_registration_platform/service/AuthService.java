package com.priyanshi.event_registration_platform.service;
import com.priyanshi.event_registration_platform.dto.LoginRequest;
import com.priyanshi.event_registration_platform.dto.RegisterRequest;
import com.priyanshi.event_registration_platform.repository.UserRepository;
import com.priyanshi.event_registration_platform.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.priyanshi.event_registration_platform.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.priyanshi.event_registration_platform.dto.LoginRequest;
@Service // i found an auth service i will create one obj and manage it
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    public AuthService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder=passwordEncoder;
    }
    public User register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user=userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User login(LoginRequest request) {
     User user=userRepository.findByEmail(request.getEmail())
             .orElseThrow(() -> new RuntimeException("Invalid email or password"));
     if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
         throw new RuntimeException("Invalid password");
     }
     return user;
    }
}
