package com.learn.simpleconsoleapp.models;

/**
 * @see com.learn.simpleconsoleapp.seedworks.AgentDecorator
 * @see com.learn.simpleconsoleapp.seedworks.AgentProfilingInterceptor
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
