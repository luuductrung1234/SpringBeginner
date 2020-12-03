package com.learn.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class MyRepository {
    public void doQuery() {
        System.out.println("Doing some query.");
    }
}
