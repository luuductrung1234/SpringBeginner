package com.learn.petclinicweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SfgPetClinicApplication {
    private static final Logger logger = LoggerFactory.getLogger(SfgPetClinicApplication.class);

    public static void main(String args[]) {
        logger.info("Hello World!");
    }
}
