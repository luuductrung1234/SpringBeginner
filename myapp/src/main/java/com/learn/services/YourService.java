package com.learn.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class YourService implements com.learn.services.Service {
    // property manual-config (using xml file)
    // require setter & getter
    @Value("${my.name}")
    private String name;

    // property manual-config (using xml file)
    // require setter & getter
    @Value("${my.city}")
    private String city;

    public YourService() {
    }

    @Override
    public void doSomething() {
        System.out.println("You:" + name + " do your job in city:" + city);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
