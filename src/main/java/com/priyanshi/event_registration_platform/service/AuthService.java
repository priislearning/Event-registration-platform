package com.priyanshi.event_registration_platform.service;
import com.priyanshi.event_registration_platform.dto.LoginRequest;
import com.priyanshi.event_registration_platform.dto.RegisterRequest;
import com.priyanshi.event_registration_platform.repository.UserRepository;
import com.priyanshi.event_registration_platform.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.priyanshi.event_registration_platform.model.Role;
import com.priyanshi.event_registration_platform.mapper.UserMapper;
import com.priyanshi.event_registration_platform.dto.LoginResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.priyanshi.event_registration_platform.dto.LoginRequest;
@Service // i found an auth service i will create one obj and manage it
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    public AuthService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder=passwordEncoder;
        this.jwtService = jwtService;
    }
    public User register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user=userMapper.toEntity(request);//convert request data into user object
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ATTENDEE);
        user.setEnabled(true);//java turns bedefault every account off
        return userRepository.save(user);
    }
    public LoginResponse login(LoginRequest request) {
     User user=userRepository.findByEmail(request.getEmail())
             .orElseThrow(() -> new RuntimeException("Invalid email or password"));
     if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
         throw new RuntimeException("Invalid password");
     }
     String token=jwtService.generateToken(user);
     return new LoginResponse(token,user);
    }
}
