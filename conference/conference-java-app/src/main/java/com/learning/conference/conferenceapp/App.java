package com.learning.conference.conferenceapp;

import com.learning.conference.conferenceapp.services.*;
import org.apache.logging.log4j.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("Hello World!");

        var context = new AnnotationConfigApplicationContext(AppConfig.class);

        var speakerService = context.getBean("speakerService", SpeakerService.class);
        var feeService = context.getBean("feeCalculateService", FeeCalculateService.class);
        logger.info("First speaker name: {} with fee: ${}", speakerService.findAll().get(0).getFirstName(), feeService.calculateFee());
    }
}
