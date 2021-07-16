package com.learn.simpleconsoleapp.seedworks.advices;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

public class ConsoleLoggingAdvice implements MethodInterceptor {
    private String expectedStringValue;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println(">> Before Invoking \"" + invocation.getMethod().getName() + "\"");
        var result = invocation.proceed();
        System.out.println(">> After Invoking \"" + invocation.getMethod().getName() + "\"");
        return result;
    }

    /**
     * This method can be used as advice, but it is not relevant to {@link org.springframework.aop.MethodBeforeAdvice} interface
     *
     * @param joinpoint
     * @param value
     * @return
     */
    public void beforeLoggingWithValue(JoinPoint joinpoint, String value) {
        beforeLogging(joinpoint);
    }

    /**
     * This method can be used as advice, but it is not relevant to {@link org.springframework.aop.MethodBeforeAdvice} interface
     *
     * @param joinpoint
     * @return
     */
    public void beforeLogging(JoinPoint joinpoint) {
        System.out.println(">> [Before] Invoking \"" + joinpoint.getSignature().getName() + "\"");
    }

    /**
     * This method can be used as advice, but it is not relevant to {@link MethodInterceptor} interface
     *
     * @param joinpoint
     * @param value runtime value
     * @return
     * @throws Throwable
     */
    public Object aroundLoggingWithValue(ProceedingJoinPoint joinpoint, String value) throws Throwable {
        if (!value.contains(expectedStringValue))
            return joinpoint.proceed();

        return aroundLogging(joinpoint);
    }

    /**
     * This method can be used as advice, but it is not relevant to {@link MethodInterceptor} interface
     *
     * @param joinpoint
     * @return
     * @throws Throwable
     */
    public Object aroundLogging(ProceedingJoinPoint joinpoint) throws Throwable {
        System.out.println(">> [Around Before] Invoking \"" + joinpoint.getSignature().getName() + "\"");

        var args = joinpoint.getArgs();
        if (args.length > 0) {
            System.out.println(">> With following arguments: ");
            Arrays.stream(args).forEach(arg -> System.out.println("\t[" + arg + "]"));
        }

        var result = joinpoint.proceed();

        System.out.println(">> [Around After] Invoking \"" + joinpoint.getSignature().getName() + "\"");
        return result;
    }

    public void setExpectedStringValue(String expectedStringValue) {
        this.expectedStringValue = expectedStringValue;
    }
}
