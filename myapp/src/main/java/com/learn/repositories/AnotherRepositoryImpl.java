package com.learn.repositories;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class AnotherRepositoryImpl implements MyRepository{
    @Override
    public void doQuery() { System.out.println("Doing some another query.");}
}
