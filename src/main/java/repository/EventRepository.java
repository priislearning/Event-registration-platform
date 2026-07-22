package com.priyanshi.event_registration_platform.repository;

import com.priyanshi.event_registration_platform.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {

}