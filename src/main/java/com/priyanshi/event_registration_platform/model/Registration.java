package com.priyanshi.event_registration_platform.model;
import jakarta.persistence.*;
@Entity//this java class represent a database table
@Table(name="registration")
public class Registration{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;//bz jpa works with objects not ids
    @ManyToOne//many registration to one user
    @JoinColumn(name="event_id")
    private Event event;
    public Registration(){}
    public Registration(User user, Event event){
        this.user = user;
        this.event = event;
    }
    public Integer getId() {
        return id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user=user;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
}