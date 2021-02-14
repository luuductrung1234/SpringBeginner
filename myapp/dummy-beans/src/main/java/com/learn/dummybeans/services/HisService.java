package com.learn.dummybeans.services;

import com.learn.dummybeans.helpers.OfficeHelper;
import com.learn.dummybeans.repositories.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class HisService implements com.learn.dummybeans.services.Service {

    // property auto-injection (using annotation)
    // not require setter & getter for property
    @Autowired
    @Qualifier("anotherRepositoryImpl")
    private MyRepository myRepository;

    @Autowired
    private OfficeHelper officeHelper;

    @Override
    public void doSomething() {
        System.out.println("He doing his job!");
        myRepository.doQuery();
        officeHelper.helpToDoSomething("clean house");
    }
}
