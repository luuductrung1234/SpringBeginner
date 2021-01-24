package com.learning.conference.conferencefirstbootapp.repositories;

import com.learning.conference.conferencefirstbootapp.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
    // derived query method
    // the naming of the method is not arbitrary - it must follow certain rules
    boolean existsSessionById(Long id);
}
