package com.learn.dummybeans.services;

import com.learn.dummybeans.repositories.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class HerService implements com.learn.dummybeans.services.Service {
    private final MyRepository myRepository;

    // constructor auto-injection
    @Autowired
    public HerService(@Qualifier("myRepositoryImpl") MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    @Override
    public void doSomething() {
        System.out.println("She doing her job!");
        myRepository.doQuery();
    }
}
