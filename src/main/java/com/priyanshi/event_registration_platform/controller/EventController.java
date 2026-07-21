package com.priyanshi.event_registration_platform.controller;

import com.priyanshi.event_registration_platform.model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController//this class contain api endpoints when spring start it finds this class
public class EventController {//constructor
   private List<Event> events = new ArrayList<>();
    public EventController() {
        events.add(new Event(1, "Spring Boot Workshop", "IIIT Pune", 50));
        events.add(new Event(2, "Hackathon", "Main Auditorium", 200));
        events.add(new Event(3, "AI Seminar", "Seminar Hall", 120));
    }
    @GetMapping("/api/events")
    public List<Event> getEvents() {
        return events;
    }
    @PostMapping("/api/events")
    public Event createEvent(@RequestBody Event event) {//public means spring can access this method Event is return type
//createEvent is method name reqbody created Event event= new Event();
        events.add(event);
        return event;

    }
    @GetMapping("/api/events/{id}")
    public Event getEvent(@PathVariable int id) {// its basically getEventById(id);
        //pathvariable spring passes value to method u never extract it manually
        for (Event event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }
    @PutMapping("/api/events/{id}")
    public Event updateEvent(@PathVariable int id,
                             @RequestBody Event updatedEvent) {//in reqbody spring converts json into an object

        for (Event event : events) {

            if (event.getId() == id) {

                event.setTitle(updatedEvent.getTitle());//event2.title=updatedevent.title
                event.setLocation(updatedEvent.getLocation());
                event.setAvailableSeats(updatedEvent.getAvailableSeats());

                return event;
            }
        }

        return null;
    }
    @DeleteMapping("/api/events/{id}")
    public Event deleteEvent(@PathVariable int id) {

        for (Event event : events) {

            if (event.getId() == id) {

                events.remove(event);

                return event;
            }
        }

        return null;
    }
}