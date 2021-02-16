package com.learn.simpleconsoleapp.configs;

import com.learn.dummybeans.repositories.MyRepositoryImpl;
import com.learn.dummybeans.services.TheirService;
import com.learn.simpleconsoleapp.beans.Singer;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.learn.dummybeans", "com.learn.moneytransfer", "com.learn.simpleconsoleapp"})
@PropertySource({"classpath:application.properties", "classpath:msf.properties"})
@ImportResource({"classpath:application-context.xml"})
@Import(SpringContainerConfig.class)
public class AppConfig {
    @Bean
    @Scope("prototype")
    public TheirService getTheirService() {
        return new TheirService(new MyRepositoryImpl());
    }

    @Bean(name = {"TimSell", "Tim", "Timmy", "Timothy"})
    public Singer singer() {
        return new Singer();
    }

}
