package com.learn.services;

import com.learn.repositories.MyRepository;
import org.springframework.stereotype.Service;

@Service
public class HerService {
    private final MyRepository myRepository;

    // constructor auto-injection
    public HerService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    public void doSomething() {
        System.out.println("She doing her job!");
        myRepository.doQuery();
    }
}
