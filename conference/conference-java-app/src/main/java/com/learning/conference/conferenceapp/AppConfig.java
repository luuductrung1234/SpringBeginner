package com.learning.conference.conferenceapp;

import com.learning.conference.conferenceapp.repository.*;
import com.learning.conference.conferenceapp.services.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan({"com.learning"})
public class AppConfig {

    // @Bean annotation is only at method level
    @Bean(name = "speakerService")
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public SpeakerService getSpeakerService() {
        return new SpeakerServiceImpl();
    }

    @Bean(name = "speakerRepository")
    //@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
    public SpeakerRepository getSpeakerRepository() {
        return new HibernateSpeakerRepositoryImpl();
    }
}
