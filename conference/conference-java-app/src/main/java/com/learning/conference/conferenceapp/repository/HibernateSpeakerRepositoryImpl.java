package com.learning.conference.conferenceapp.repository;

import com.learning.conference.conferenceapp.model.Speaker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HibernateSpeakerRepositoryImpl implements SpeakerRepository {
    private static Logger logger = LogManager.getLogger();

    @Autowired
    private Calendar calendar;

    @Value("#{ T(java.lang.Math).random() * 100 }")
    private double seedNum;

    @Override
    public List<Speaker> findAll() {
        var speakers = new ArrayList<Speaker>() {{
            add(new Speaker("Tom", "Luu", seedNum));
            add(new Speaker("Jane", "Hill", seedNum));
            add(new Speaker("Moon", "Tana", seedNum));
        }};

        logger.info("Cal: {}", calendar.getTime());

        return speakers;
    }
}
