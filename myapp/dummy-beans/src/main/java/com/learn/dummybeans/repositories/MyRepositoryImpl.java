package com.learn.dummybeans.repositories;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class MyRepositoryImpl implements MyRepository, EnvironmentAware {
    @Override
    public void doQuery() {
        System.out.println("Doing some query.");
    }

    /**
     * @Implement {@link EnvironmentAware#setEnvironment(Environment)}
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {

    }
}
