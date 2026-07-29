package com.priyanshi.event_registration_platform.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Integer id) {
        super("User with id " + id + " not found");
    }
}