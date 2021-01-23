package com.learning.conference.conferenceapp.repository;

import com.learning.conference.conferenceapp.model.Speaker;

import java.util.List;

public interface SpeakerRepository {
    List<Speaker> findAll();
}
