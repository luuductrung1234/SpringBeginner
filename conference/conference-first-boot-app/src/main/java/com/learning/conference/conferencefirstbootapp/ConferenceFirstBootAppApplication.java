package com.learning.conference.conferencefirstbootapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ConferenceFirstBootAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConferenceFirstBootAppApplication.class, args);
    }
}
