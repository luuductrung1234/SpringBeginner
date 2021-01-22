package com.learning.conference.conferenceapp.util;

import org.springframework.beans.factory.FactoryBean;

import java.util.Calendar;

public class CalendarFactory implements FactoryBean<Calendar> {
    private Calendar instance = Calendar.getInstance();

    @Override
    public Calendar getObject() throws Exception {
        return instance;
    }

    @Override
    public Class<?> getObjectType() {
        return Calendar.class;
    }

    public void addDays(int dayNum){
        instance.add(Calendar.DAY_OF_YEAR, dayNum);
    }
}
