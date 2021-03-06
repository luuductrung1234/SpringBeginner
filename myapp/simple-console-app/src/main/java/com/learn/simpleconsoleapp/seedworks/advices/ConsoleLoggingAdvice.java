package com.learn.simpleconsoleapp.seedworks.advices;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ConsoleLoggingAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println(">> Before Invoking " + invocation.getMethod().getName());
        var result = invocation.proceed();
        System.out.println(">> Done " + invocation.getMethod().getName());
        return result;
    }
}
