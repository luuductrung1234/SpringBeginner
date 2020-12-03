package com.learn.repositories;

import org.springframework.stereotype.Component;

@Component
public class MyRepository {
    public void doQuery() {
        System.out.println("Doing some query.");
    }
}
