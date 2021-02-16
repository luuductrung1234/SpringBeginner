package com.learn.simpleconsoleapp.beans;

import org.springframework.stereotype.Component;

@Component
@Trophy(name = {"grammy", "platinum disk"})
public class BetterSinger extends Singer {
    private String lyric = "We found a message in a bottle we were drinking";

    @Override
    public void sing() {
        System.out.println("SINGING BETTER: " + lyric);
    }
}
