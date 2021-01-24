package com.learning.conference.conferencefirstbootapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ConferenceFirstBootAppApplication extends SpringBootServletInitializer {
    private Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        SpringApplication.run(ConferenceFirstBootAppApplication.class, args);
    }

    @Value("${spring.datasource.url}")
    private String envVar;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String[] arg0) throws Exception {
                logger.warn("{}", envVar);
            }
        };
    }
}
