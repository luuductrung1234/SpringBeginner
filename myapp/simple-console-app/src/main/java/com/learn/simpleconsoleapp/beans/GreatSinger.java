package com.learn.simpleconsoleapp.beans;

import org.springframework.stereotype.Component;

@Component
@Trophy(name = {"grammy", "diamond disk"})
public class GreatSinger extends Singer {
    private String lyric = "We found a message in a bottle we were drinking";
    protected float incomePercent = 0.9f;

    @Override
    public void sing() {
        System.out.println("SINGING THE BEST: " + lyric);
    }

    @Override
    public void rent(float salary) {
        System.out.println("GREAT SINGER dealing salary for: $" + salary);
        super.rent(salary * incomePercent);
    }
}
