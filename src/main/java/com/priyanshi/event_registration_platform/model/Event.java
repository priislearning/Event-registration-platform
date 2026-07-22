package com.priyanshi.event_registration_platform.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
@Entity
public class Event {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String location;
    private int availableSeats;

    public Event() {
    }
    public Event(int id, String title, String location, int availableSeats) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.availableSeats = availableSeats;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }


    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
