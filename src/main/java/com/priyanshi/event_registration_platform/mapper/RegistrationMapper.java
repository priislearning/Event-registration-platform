package com.priyanshi.event_registration_platform.mapper;

import com.priyanshi.event_registration_platform.dto.RegistrationResponse;
import com.priyanshi.event_registration_platform.model.Registration;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {

    public RegistrationResponse toResponse(Registration registration) {

        RegistrationResponse response = new RegistrationResponse();

        response.setId(registration.getId());

        response.setUserName(
                registration.getUser().getName()
        );

        response.setEventTitle(
                registration.getEvent().getTitle()
        );

        return response;
    }
}