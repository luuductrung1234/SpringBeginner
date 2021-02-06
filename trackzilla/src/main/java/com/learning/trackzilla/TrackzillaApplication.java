package com.learning.trackzilla;

import com.learning.trackzilla.entity.Application;
import com.learning.trackzilla.repository.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrackzillaApplication {
    private static final Logger logger = LoggerFactory.getLogger(TrackzillaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TrackzillaApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner seedData(ApplicationRepository applicationRepository){
//        return (args -> {
//            applicationRepository.save(new Application("Trackzilla", "tom.luu", "Application for tracking bugs."));
//            applicationRepository.save(new Application("Expenses", "mary.jones", "Application to track expense reports."));
//            applicationRepository.save(new Application("Notifications", "karen.kane", "Application to send alerts and notifications to users."));
//
//            for (Application application : applicationRepository.findAll()) {
//                logger.info("The application is: " + application.toString());
//            }
//        });
//    }
}
