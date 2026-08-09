package com.priyanshi.event_registration_platform.controller;

import com.priyanshi.event_registration_platform.dto.CreateRegistrationRequest;
import com.priyanshi.event_registration_platform.model.Registration;
import com.priyanshi.event_registration_platform.service.RegistrationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public Registration registerUser(
            @RequestBody CreateRegistrationRequest request) {
        return registrationService.registerUser(
                request.getUserId(),
                request.getEventId()
        );
    }
    @GetMapping
    public List<Registration> getAllRegistrations() {
        return registrationService.getAllRegistrations();
    }
}