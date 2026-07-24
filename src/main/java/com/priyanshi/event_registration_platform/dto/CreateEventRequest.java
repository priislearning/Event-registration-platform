package com.priyanshi.event_registration_platform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CreateEventRequest {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @Positive(message = "Available seats must be greater than 0")
    private int availableSeats;

    public CreateEventRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}