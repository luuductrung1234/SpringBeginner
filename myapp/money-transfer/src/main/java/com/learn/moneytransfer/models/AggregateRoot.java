package com.learn.moneytransfer.models;

import org.springframework.context.ApplicationEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot {
    private final List<ApplicationEvent> events;

    protected AggregateRoot() {
        events = new ArrayList<>();
    }

    public void addEvent(ApplicationEvent event) {
        events.add(event);
    }

    public List<ApplicationEvent> getEvents() {
        var result = new ArrayList<>(events);
        events.clear();
        return result;
    }
}
