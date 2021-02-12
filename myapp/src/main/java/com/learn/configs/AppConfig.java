package com.learn.configs;

import com.learn.repositories.MyRepositoryImpl;
import com.learn.seedworks.InspectRegisteredScopesPostProcessor;
import com.learn.services.TheirService;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.context.support.SimpleThreadScope;

@Configuration
@ComponentScan(basePackages = "com.learn")
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Bean
    @Scope("prototype")
    public TheirService getTheirService() {
        return new TheirService(new MyRepositoryImpl());
    }

    @Bean
    public static CustomScopeConfigurer getCustomScopeConfigurer(){
        var customScopeConfigurer = new CustomScopeConfigurer();
        customScopeConfigurer.addScope("thread", new SimpleThreadScope());
        return customScopeConfigurer;
    }

    @Bean
    public static InspectRegisteredScopesPostProcessor getInspectScopesPostProcessor(){
        return new InspectRegisteredScopesPostProcessor();
    }
}
