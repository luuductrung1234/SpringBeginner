package com.learning.conference.conferencexmlapp.repository;

import java.util.ArrayList;
import java.util.List;

public class FeeScheduleRepositoryImpl implements FeeScheduleRepository {
    @Override
    public List<Double> getAll() {
        return new ArrayList<>() {{
            add(10.6d);
            add(1.0d);
            add(2.45d);
            add(45.0d);
        }};
    }
}
