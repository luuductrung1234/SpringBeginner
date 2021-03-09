package com.learn.simpleconsoleapp.seedworks.pointcuts;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SupervisorReviewStaticPointcut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return "review".equals(method.getName());
    }
}
