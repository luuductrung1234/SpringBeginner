package com.learn.helpers;

import org.springframework.stereotype.Component;

@Component
public class OfficeHelper {
    public OfficeHelper() {
        name = "Jenny";
    }

    public OfficeHelper(String name) {
        this.name = name;
    }

    private String name;

    public void helpToDoSomething(String task) {
        System.out.println(name + " help to do " + task);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
