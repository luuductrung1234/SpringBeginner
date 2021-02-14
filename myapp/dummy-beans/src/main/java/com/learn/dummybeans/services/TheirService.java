package com.learn.dummybeans.services;

import com.learn.dummybeans.repositories.MyRepository;

public class TheirService implements com.learn.dummybeans.services.Service {
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
