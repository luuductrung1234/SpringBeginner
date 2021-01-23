package com.learning.conference.conferenceapp.services;

import com.learning.conference.conferenceapp.model.Speaker;
import com.learning.conference.conferenceapp.repository.SpeakerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

public class SpeakerServiceImpl implements SpeakerService {
    private static Logger logger = LogManager.getLogger();

    @Autowired
    private SpeakerRepository speakerRepository;

    public SpeakerServiceImpl() {
        logger.info("Create {} without any constructor arguments", SpeakerServiceImpl.class.getSimpleName());
    }

    @PostConstruct
    private void initialize(){
        logger.info("Call after constructor happen");
    }

    @Override
    public List<Speaker> findAll() {
        return speakerRepository.findAll();
    }
}
