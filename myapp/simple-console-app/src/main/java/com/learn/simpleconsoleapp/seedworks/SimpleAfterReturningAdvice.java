package com.learn.simpleconsoleapp.seedworks;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class SimpleAfterReturningAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("After executed: " + method.getName());
    }
}
