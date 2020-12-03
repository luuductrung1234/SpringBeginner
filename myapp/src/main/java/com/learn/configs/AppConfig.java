package com.learn.configs;

import com.learn.repositories.MyRepositoryImpl;
import com.learn.services.TheirService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = "com.learn")
public class AppConfig {
    @Bean
    @Scope("prototype")
    public TheirService getTheirService() {
        return new TheirService(new MyRepositoryImpl());
    }
}
