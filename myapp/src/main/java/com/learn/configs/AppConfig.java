package com.learn.configs;

import com.learn.repositories.MyRepositoryImpl;
import com.learn.services.TheirService;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.learn")
@PropertySource("classpath:application.properties")
@Import(SpringContainerConfig.class)
public class AppConfig {
    @Bean
    @Scope("prototype")
    public TheirService getTheirService() {
        return new TheirService(new MyRepositoryImpl());
    }

}
