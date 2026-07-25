package com.priyanshi.event_registration_platform.mapper;

import com.priyanshi.event_registration_platform.dto.CreateEventRequest;
import com.priyanshi.event_registration_platform.dto.EventResponse;
import com.priyanshi.event_registration_platform.model.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public Event toEntity(CreateEventRequest request) {

        Event event = new Event();

        event.setTitle(request.getTitle());
        event.setLocation(request.getLocation());
        event.setAvailableSeats(request.getAvailableSeats());

        return event;
    }

    public EventResponse toResponse(Event event) {

        EventResponse response = new EventResponse();

        response.setId(event.getId());
        response.setTitle(event.getTitle());
        response.setLocation(event.getLocation());
        response.setAvailableSeats(event.getAvailableSeats());

        return response;
    }
}