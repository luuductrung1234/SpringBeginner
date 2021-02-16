package com.learn.simpleconsoleapp.beans;

import org.springframework.stereotype.Component;

@Component
@Award(prize = {"grammy", "platinum disk"})
public class Singer {
    private String lyric = "Down there below us, under the clouds";

    public void sing() {
        System.out.println(lyric);
    }
}
