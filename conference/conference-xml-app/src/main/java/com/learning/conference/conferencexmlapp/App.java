package com.learning.conference.conferencexmlapp;

import com.learning.conference.conferencexmlapp.services.*;
import org.apache.logging.log4j.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("Hello World!");

        var context = new ClassPathXmlApplicationContext("appContext.xml");

        var speakerService = context.getBean("speakerService", SpeakerService.class);
        var feeService = context.getBean("feeCalculateService", FeeCalculateService.class);
        logger.info("First speaker name: {} with fee: ${}", speakerService.findAll().get(0).getFirstName(), feeService.calculateFee());
    }
}
