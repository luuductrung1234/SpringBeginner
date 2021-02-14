package com.learn.dummybeans.repositories;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
@Scope("singleton")
public class AnotherRepositoryImpl implements MyRepository, EnvironmentAware {
    private String[] activeProfiles;
    private String connectionString;

    @Override
    public void doQuery() {
        System.out.println("Active profiles: " + Arrays.toString(activeProfiles));
        System.out.println("Doing some another query.");
    }

    @Override
    public void setEnvironment(Environment environment) {
        activeProfiles = environment.getActiveProfiles();
    }
}
