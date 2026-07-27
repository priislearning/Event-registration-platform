package com.priyanshi.event_registration_platform.repository;

import com.priyanshi.event_registration_platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}