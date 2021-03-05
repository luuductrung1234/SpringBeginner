package com.learn.simpleconsoleapp.seedworks;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * {@link MethodInterceptor} interface is a standard AOP Alliance interface for implementing
 * Around Advice for method invocation joinpoints.
 *
 * @see com.learn.simpleconsoleapp.models.Agent
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
