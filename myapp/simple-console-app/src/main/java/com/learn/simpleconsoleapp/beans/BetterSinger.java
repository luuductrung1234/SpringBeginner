package com.learn.simpleconsoleapp.beans;

import com.learn.simpleconsoleapp.seedworks.annotation.NeedLogging;
import org.springframework.stereotype.Component;

@Component
@Trophy(name = {"grammy", "platinum disk"})
public class BetterSinger extends Singer implements Supervisor {
    private String lyric = "We found a message in a bottle we were drinking";
    private float incomePercent = 0.7f;

    @Override
    public void sing() {
        System.out.println("SINGING BETTER: " + lyric);
    }

    @Override
    public void rent(float salary) {
        System.out.println("BETTER SINGER dealing salary");
        super.rent(salary * incomePercent);
    }

    @NeedLogging
    public void setupShow() {
        System.out.println("BETTER SINGER setup a show");
    }

    @Override
    public void review() {
        // No-op
    }

    @Override
    public void training() {
        // No-op
    }
}
