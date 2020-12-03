package com.learn.services;

import com.learn.repositories.MyRepository;

public class TheirService implements com.learn.services.Service {
    private MyRepository myRepository;

    public TheirService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    @Override
    public void doSomething() {
        System.out.println("They doing their job!");
        myRepository.doQuery();
    }
}
