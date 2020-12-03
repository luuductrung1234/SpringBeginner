package com.learn.services;

import com.learn.repositories.MyRepositoryImpl;

public class MyService {
    private MyRepositoryImpl myRepository;

    // constructor manual-injection
    public MyService(MyRepositoryImpl myRepository) {
        this.myRepository = myRepository;
    }

    public void doSomething(){
        System.out.println("Doing something important!");
        myRepository.doQuery();
    }
}
