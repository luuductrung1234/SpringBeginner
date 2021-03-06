package com.learn.simpleconsoleapp.seedworks.advices;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

/**
 * @see com.learn.simpleconsoleapp.models.Agent
 */
public class AgentProfilingInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(invocation.getMethod().getName());
        Object returnValue = invocation.proceed();
        sw.stop();
        invocationInfo(invocation, sw.getTotalTimeMillis());
        return returnValue;
    }

    private void invocationInfo(MethodInvocation invocation, long ms) {
        Method m = invocation.getMethod();
        Object target = invocation.getThis();
        Object[] args = invocation.getArguments();
        System.out.println("Executed method: " + m.getName());
        System.out.println("On object of type: " +
                target.getClass().getName());
        System.out.println("With arguments:");
        for (int x = 0; x < args.length; x++) {
            System.out.print(" > " + args[x]);
        }
        System.out.print("\n");
        System.out.println("Took: " + ms + " ms");
    }
}
