package com.priyanshi.event_registration_platform.service;
import com.priyanshi.event_registration_platform.dto.CreateEventRequest;
import com.priyanshi.event_registration_platform.model.Event;
import com.priyanshi.event_registration_platform.repository.EventRepository;
import org.springframework.stereotype.Service;
import com.priyanshi.event_registration_platform.exception.EventNotFoundException;
import java.util.List;
import java.util.Optional;
import com.priyanshi.event_registration_platform.dto.CreateEventRequest;
import com.priyanshi.event_registration_platform.dto.EventResponse;
@Service
public class EventService {

    private final EventRepository repository;
    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public List<Event> getAllEvents() {
        return repository.findAll();
    }

    public Event getEventById(int id) {
        Optional<Event> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new EventNotFoundException("Event with id " + id + " not found");
        }

        return optional.get();
    }

    public EventResponse createEvent(CreateEventRequest request) {

        Event event = new Event();

        event.setTitle(request.getTitle());
        event.setLocation(request.getLocation());
        event.setAvailableSeats(request.getAvailableSeats());

        Event savedEvent = repository.save(event);

        EventResponse response = new EventResponse();

        response.setId(savedEvent.getId());
        response.setTitle(savedEvent.getTitle());
        response.setLocation(savedEvent.getLocation());
        response.setAvailableSeats(savedEvent.getAvailableSeats());

        return response;
    }

    public Event updateEvent(int id, Event updatedEvent) {

        Event event=getEventById(id);
        event.setTitle(updatedEvent.getTitle());
        event.setLocation(updatedEvent.getLocation());
        event.setAvailableSeats(updatedEvent.getAvailableSeats());
        return repository.save(event);
    }

    public void deleteEvent(int id) {
        Event event = getEventById(id);
        repository.deleteById(id);
    }
}