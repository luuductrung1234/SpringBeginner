package com.learning.conference.conferencefirstbootapp.repositories;

import com.learning.conference.conferencefirstbootapp.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
    boolean existsSessionById(Long id);
}
