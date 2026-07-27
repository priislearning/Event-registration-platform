package com.priyanshi.event_registration_platform.service;
import com.priyanshi.event_registration_platform.dto.CreateEventRequest;
import com.priyanshi.event_registration_platform.model.Event;
import com.priyanshi.event_registration_platform.repository.EventRepository;
import org.springframework.stereotype.Service;
import com.priyanshi.event_registration_platform.exception.EventNotFoundException;
import java.util.List;
import com.priyanshi.event_registration_platform.mapper.EventMapper;
import java.util.Optional;

import com.priyanshi.event_registration_platform.dto.EventResponse;
@Service
public class  EventService {



    private Event findEventById(int id) {
        Optional<Event> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new EventNotFoundException("Event with id " + id + " not found");
        }

        return optional.get();
    }
    private final EventRepository repository;
    private final EventMapper mapper;

    public EventService(EventRepository repository, EventMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<EventResponse> getAllEvents() {
        return repository.findAll().stream().map(event->mapper.toResponse(event)).toList();
    }

    public EventResponse getEventById(int id) {
        Event event=findEventById(id);
        return mapper.toResponse(event);
    }

    public EventResponse createEvent(CreateEventRequest request) {

            Event event = mapper.toEntity(request);

            Event savedEvent = repository.save(event);

            return mapper.toResponse(savedEvent);
        }

    public EventResponse updateEvent(int id,CreateEventRequest request) {

        Event event=findEventById(id);
        event.setTitle(request.getTitle());
        event.setLocation(request.getLocation());
        event.setAvailableSeats(request.getAvailableSeats());
        Event updated = repository.save(event);

        return mapper.toResponse(updated);
    }

    public void deleteEvent(int id) {
        Event event = findEventById(id);
        repository.deleteById(id);
    }
}