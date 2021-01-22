package com.learning.conference.conferencexmlapp.repository;

import com.learning.conference.conferencexmlapp.model.Speaker;

import java.util.List;

public interface SpeakerRepository {
    List<Speaker> findAll();
}
