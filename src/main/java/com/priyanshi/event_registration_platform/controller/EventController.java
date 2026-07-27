package com.priyanshi.event_registration_platform.controller;

import com.priyanshi.event_registration_platform.dto.CreateEventRequest;
import com.priyanshi.event_registration_platform.model.Event;
import com.priyanshi.event_registration_platform.service.EventService;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;
import jakarta.validation.Valid;
import com.priyanshi.event_registration_platform.dto.EventResponse;
@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/api/events")
    public List<EventResponse> getEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/api/events/{id}")
    public EventResponse getEvent(@PathVariable int id) {
        return eventService.getEventById(id);
    }

    @PostMapping("/api/events")
    public EventResponse createEvent(@Valid @RequestBody CreateEventRequest request) {
        return eventService.createEvent(request);
    }

    @PutMapping("/api/events/{id}")
    public EventResponse updateEvent(@PathVariable int id,
                             @Valid @RequestBody CreateEventRequest request) {

        return eventService.updateEvent(id, request);
    }

    @DeleteMapping("/api/events/{id}")
    public void deleteEvent(@PathVariable int id) {
        eventService.deleteEvent(id);
    }
}