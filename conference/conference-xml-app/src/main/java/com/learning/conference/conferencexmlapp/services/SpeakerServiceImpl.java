package com.learning.conference.conferencexmlapp.services;

import com.learning.conference.conferencexmlapp.model.Speaker;
import com.learning.conference.conferencexmlapp.repository.HibernateSpeakerRepositoryImpl;
import com.learning.conference.conferencexmlapp.repository.SpeakerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SpeakerServiceImpl implements SpeakerService {
    private static Logger logger = LogManager.getLogger();

    private SpeakerRepository speakerRepository;

    public SpeakerServiceImpl() {
        logger.info("Create {} without any constructor arguments", SpeakerServiceImpl.class.getSimpleName());
    }

    @Override
    public List<Speaker> findAll() {
        return speakerRepository.findAll();
    }

    public void setSpeakerRepository(SpeakerRepository speakerRepository) {
        logger.info("inject dependency {} into {}", HibernateSpeakerRepositoryImpl.class.getSimpleName(), SpeakerServiceImpl.class.getSimpleName());
        this.speakerRepository = speakerRepository;
    }
}
