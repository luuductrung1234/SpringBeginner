package com.learn.simpleconsoleapp.seedworks.advisor;

import com.learn.simpleconsoleapp.seedworks.advices.IsModifiedMixinIntroduction;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class IsModifiedAdvisor extends DefaultIntroductionAdvisor {
    public IsModifiedAdvisor() {
        super(new IsModifiedMixinIntroduction());
    }
}
