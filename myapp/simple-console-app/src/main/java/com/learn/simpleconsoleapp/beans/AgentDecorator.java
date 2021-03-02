package com.learn.simpleconsoleapp.beans;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * {@link MethodInterceptor} interface is a standard AOP Alliance interface for implementating
 * around advice for method invocation joinpoints.
 */
public class AgentDecorator implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.print("James ");

        var retVal = invocation.proceed();

        System.out.print("!");

        return retVal;
    }
}
