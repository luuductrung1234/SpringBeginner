package com.learn.simpleconsoleapp.configs;

import com.learn.dummybeans.repositories.MyRepositoryImpl;
import com.learn.dummybeans.services.TheirService;
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
