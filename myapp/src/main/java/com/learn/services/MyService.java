package com.learn.services;

import com.learn.repositories.MyRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class MyService implements com.learn.services.Service {
    private MyRepositoryImpl myRepository;

    // constructor manual-injection
    public MyService(MyRepositoryImpl myRepository) {
        this.myRepository = myRepository;
    }

    @Override
    public void doSomething() {
        System.out.println("Me do my job!");
        myRepository.doQuery();
    }
}
