package com.learn.simpleconsoleapp.beans;

import org.springframework.stereotype.Component;

@Component
public class Agent {
    public void speak() {
        System.out.print("Bond");
    }
}
