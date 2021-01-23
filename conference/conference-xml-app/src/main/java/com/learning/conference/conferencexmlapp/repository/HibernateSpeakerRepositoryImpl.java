package com.learning.conference.conferencexmlapp.repository;


import com.learning.conference.conferencexmlapp.model.Speaker;

import java.util.ArrayList;
import java.util.List;

public class HibernateSpeakerRepositoryImpl implements SpeakerRepository {
    @Override
    public List<Speaker> findAll() {
        var speakers = new ArrayList<Speaker>() {{
            add(new Speaker("Tom", "Luu"));
            add(new Speaker("Jane", "Hill"));
            add(new Speaker("Moon", "Tana"));
        }};

        return speakers;
    }
}
