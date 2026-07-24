package com.priyanshi.event_registration_platform.controller;

import com.priyanshi.event_registration_platform.dto.CreateEventRequest;
import com.priyanshi.event_registration_platform.model.Event;
import com.priyanshi.event_registration_platform.service.EventService;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;
import jakarta.validation.Valid;

@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/api/events")
    public List<Event> getEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/api/events/{id}")
    public Event getEvent(@PathVariable int id) {
        return eventService.getEventById(id);
    }

    @PostMapping("/api/events")
    public Event createEvent(@Valid @RequestBody CreateEventRequest request) {
        return eventService.createEvent(request);
    }

    @PutMapping("/api/events/{id}")
    public Event updateEvent(@PathVariable int id,
                             @Valid @RequestBody Event updatedEvent) {

        return eventService.updateEvent(id, updatedEvent);
    }

    @DeleteMapping("/api/events/{id}")
    public void deleteEvent(@PathVariable int id) {
        eventService.deleteEvent(id);
    }
}