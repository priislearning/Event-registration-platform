package com.priyanshi.event_registration_platform.service;

import com.priyanshi.event_registration_platform.model.Event;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    private List<Event> events = new ArrayList<>();

    public EventService() {
        events.add(new Event(1, "Spring Boot Workshop", "IIIT Pune", 50));
        events.add(new Event(2, "Hackathon", "Main Auditorium", 200));
        events.add(new Event(3, "AI Seminar", "Seminar Hall", 120));
    }

    public List<Event> getAllEvents() {
        return events;
    }

    public Event getEventById(int id) {
        for (Event event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }

    public Event createEvent(Event event) {
        events.add(event);
        return event;
    }

    public Event updateEvent(int id, Event updatedEvent) {

        for (Event event : events) {

            if (event.getId() == id) {

                event.setTitle(updatedEvent.getTitle());
                event.setLocation(updatedEvent.getLocation());
                event.setAvailableSeats(updatedEvent.getAvailableSeats());

                return event;
            }
        }

        return null;
    }

    public Event deleteEvent(int id) {

        for (Event event : events) {

            if (event.getId() == id) {

                events.remove(event);

                return event;
            }
        }

        return null;
    }
}