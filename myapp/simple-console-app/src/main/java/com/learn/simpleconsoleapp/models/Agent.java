package com.learn.simpleconsoleapp.models;

import com.learn.simpleconsoleapp.seedworks.advices.AgentDecorator;
import com.learn.simpleconsoleapp.seedworks.advices.AgentProfilingInterceptor;

/**
 * @see AgentDecorator
 * @see AgentProfilingInterceptor
 */
public class Agent {
    public void speak() {
        System.out.print("Bond");
    }

    public void shoot() {
        System.out.print("pew pew");
    }

    public void doingMission() {
        var noOfTimes = 1000;
        System.out.println("Agent doing mission");
        for(int x = 0; x < noOfTimes; x++) {
            System.out.print(". ");
        }
        System.out.println("");
        System.out.println("Agent completed mission");
    }
}
