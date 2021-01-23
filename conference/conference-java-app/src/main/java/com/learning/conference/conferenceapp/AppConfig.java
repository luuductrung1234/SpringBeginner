package com.learning.conference.conferenceapp;

import com.learning.conference.conferenceapp.repository.*;
import com.learning.conference.conferenceapp.services.*;
import com.learning.conference.conferenceapp.util.CalendarFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Calendar;

@Configuration
@ComponentScan({"com.learning"})
public class AppConfig {

    @Bean("calendarFactory")
    public CalendarFactory calendarFactory() {
        CalendarFactory factory = new CalendarFactory();
        factory.addDays(2);
        return factory;
    }

    @Bean
    public Calendar calendar() throws Exception {
        return calendarFactory().getObject();
    }

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
