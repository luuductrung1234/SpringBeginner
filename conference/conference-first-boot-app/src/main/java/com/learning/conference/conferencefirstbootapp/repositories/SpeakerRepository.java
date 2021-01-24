package com.learning.conference.conferencefirstbootapp.repositories;

import com.learning.conference.conferencefirstbootapp.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
}
