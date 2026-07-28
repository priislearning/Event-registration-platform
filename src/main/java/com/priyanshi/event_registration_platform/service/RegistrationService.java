package com.priyanshi.event_registration_platform.service;

import com.priyanshi.event_registration_platform.model.Event;
import com.priyanshi.event_registration_platform.model.Registration;
import com.priyanshi.event_registration_platform.model.User;
import com.priyanshi.event_registration_platform.repository.EventRepository;
import com.priyanshi.event_registration_platform.repository.RegistrationRepository;
import com.priyanshi.event_registration_platform.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    public RegistrationService(
            RegistrationRepository registrationRepository,
            UserRepository userRepository,
            EventRepository eventRepository
    ) {

        this.registrationRepository = registrationRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;

    }


    public Registration registerUser(Integer userId, Integer eventId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Registration registration = new Registration();

        registration.setUser(user);
        registration.setEvent(event);

        return registrationRepository.save(registration);
    }
}