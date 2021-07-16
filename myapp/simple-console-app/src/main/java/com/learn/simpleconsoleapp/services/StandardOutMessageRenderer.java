package com.learn.simpleconsoleapp.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StandardOutMessageRenderer implements MessageRenderer {
    private final MessageProvider messageProvider;

    public StandardOutMessageRenderer(@Qualifier("configurable") MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @Override
    public void render() {
        if (messageProvider == null) {
            throw new RuntimeException("You must provide " + MessageProvider.class.getName());
        }
        System.out.println(messageProvider.getMessage());
    }

    @Override
    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }
}
