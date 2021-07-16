package com.learn.moneytransfer.publishers;

import org.springframework.context.ApplicationEvent;

public interface EventPublisher {
    void publish(ApplicationEvent event);
}
