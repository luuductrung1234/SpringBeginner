package com.learn.services;

import com.learn.repositories.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HerService {
    private final MyRepository myRepository;

    public HerService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    public void doSomething() {
        System.out.println("Doing her job!");
        myRepository.doQuery();
    }
}
