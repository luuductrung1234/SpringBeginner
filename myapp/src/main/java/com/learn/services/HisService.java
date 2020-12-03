package com.learn.services;

import com.learn.repositories.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HisService {
    @Autowired
    private MyRepository myRepository;

    public void doSomething() {
        System.out.println("Doing his job!");
        myRepository.doQuery();
    }
}
