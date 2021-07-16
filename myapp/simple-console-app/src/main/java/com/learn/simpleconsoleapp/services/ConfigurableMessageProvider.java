package com.learn.simpleconsoleapp.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Qualifier("configurable")
public class ConfigurableMessageProvider implements MessageProvider {
    private String message;

    public ConfigurableMessageProvider(@Value("${message.provider.msg}") String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
