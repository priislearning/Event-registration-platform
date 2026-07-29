package com.priyanshi.event_registration_platform.dto;

public class CreateRegistrationRequest {

    private Integer userId;

    private Integer eventId;

    public CreateRegistrationRequest() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
}