package com.learn.simpleconsoleapp;

import com.learn.simpleconsoleapp.beans.Song;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HierarchicalAppContextApp {
    public static void main(String... args) {
        GenericXmlApplicationContext parent = new GenericXmlApplicationContext();
        parent.load("classpath:spring/parent-context.xml");
        parent.refresh();

        GenericXmlApplicationContext child = new GenericXmlApplicationContext();
        child.load("classpath:spring/child-context.xml");
        // if we not set parent context, some parent's beans reference in child context will not found
        // it causes an error
        child.setParent(parent);
        child.refresh();

        Song song1 = (Song) child.getBean("song1");
        Song song2 = (Song) child.getBean("song2");
        Song song3 = (Song) child.getBean("song3");

        System.out.println("from child ctx: " + song1.getTitle());
        System.out.println("from parent ctx: " + song2.getTitle());
        System.out.println("from parent ctx: " + song3.getTitle());

        child.close();
        parent.close();
    }
}
