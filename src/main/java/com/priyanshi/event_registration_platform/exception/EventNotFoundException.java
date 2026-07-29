package com.priyanshi.event_registration_platform.exception;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(Integer id) {
        super("Event with id " + id + " not found");
    }
}