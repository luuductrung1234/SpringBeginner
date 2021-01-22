package com.learning.conference.conferenceapp.services;

import com.learning.conference.conferenceapp.model.Speaker;

import java.util.List;

public interface SpeakerService {
    List<Speaker> findAll();
}
