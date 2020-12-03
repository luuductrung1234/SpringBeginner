package com.learn.services;

import com.learn.repositories.MyRepository;

public class MyService {
    private MyRepository myRepository;

    // constructor manual-injection
    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    public void doSomething(){
        System.out.println("Doing something important!");
        myRepository.doQuery();
    }
}