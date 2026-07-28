package com.priyanshi.event_registration_platform.repository;

import com.priyanshi.event_registration_platform.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository
        extends JpaRepository<Registration,Integer> {

}