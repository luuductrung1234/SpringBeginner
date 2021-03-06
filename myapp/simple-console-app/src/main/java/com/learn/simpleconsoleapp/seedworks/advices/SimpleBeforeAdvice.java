package com.learn.simpleconsoleapp.seedworks.advices;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SimpleBeforeAdvice implements MethodBeforeAdvice {
    /**
     * implement {@link MethodBeforeAdvice}, which the AOP framework calls before the method
     * at the joinpoint is invoked.
     *
     * @param method that will be invoked
     * @param args that will be passed to that method
     * @param target that is the target of the invocation
     * @throws Throwable
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Before execute: " + method.getName());
    }
}
