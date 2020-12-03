package com.learn.services;

import com.learn.helpers.OfficeHelper;
import com.learn.repositories.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HisService {

    // property auto-injection (using annotation)
    // not require setter & getter for property
    @Autowired
    private MyRepository myRepository;

    @Autowired
    private OfficeHelper officeHelper;

    public void doSomething() {
        System.out.println("He doing his job!");
        myRepository.doQuery();
        officeHelper.helpToDoSomething("clean house");
    }
}
