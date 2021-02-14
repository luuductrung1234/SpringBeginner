package com.learn.simpleconsoleapp.configs;

import com.learn.simpleconsoleapp.seedworks.InspectRegisteredScopesPostProcessor;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.SimpleThreadScope;

@Configuration
public class SpringContainerConfig {
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
