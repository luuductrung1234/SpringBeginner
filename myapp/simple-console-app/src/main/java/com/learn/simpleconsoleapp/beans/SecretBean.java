package com.learn.simpleconsoleapp.beans;

import org.springframework.stereotype.Component;

@Component
public class SecretBean {
    public void writeSecretMessage() {
        System.out.println("Every time I learn something new, " +
                "it pushed some old stuff out of my brain.");
    }
}
