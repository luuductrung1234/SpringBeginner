package com.learn.services;

public class YourService {
    private String name;

    public YourService() {
        name = "John Doe";
    }

    public YourService(String name) {
        this.name = name;
    }

    public void doSomething() {
        System.out.println("Hello " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
