package com.priyanshi.event_registration_platform.service;
import org.springframework.transaction.annotation.Transactional;
import com.priyanshi.event_registration_platform.model.Event;
import com.priyanshi.event_registration_platform.model.Registration;
import com.priyanshi.event_registration_platform.model.User;
import com.priyanshi.event_registration_platform.repository.EventRepository;
import com.priyanshi.event_registration_platform.repository.RegistrationRepository;
import com.priyanshi.event_registration_platform.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.priyanshi.event_registration_platform.exception.UserNotFoundException;
import com.priyanshi.event_registration_platform.exception.EventNotFoundException;
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

    @Transactional
    public Registration registerUser(Integer userId, Integer eventId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with id " + userId + " not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event with id " + eventId + " not found"));
        if(registrationRepository.existsByUserIdAndEventId(userId,eventId)){
            throw new RuntimeException("User with id " + userId + " and event id " + eventId + " already exists");
        }
        if(event.getAvailableSeats() <= 0){
            throw new RuntimeException("Event is full");
        }


        Registration registration = new Registration();

        registration.setUser(user);
        registration.setEvent(event);
        event.setAvailableSeats(event.getAvailableSeats() - 1);
        eventRepository.save(event);
        return registrationRepository.save(registration);
    }
}