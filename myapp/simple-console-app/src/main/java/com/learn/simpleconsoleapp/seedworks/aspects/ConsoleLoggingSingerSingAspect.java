package com.learn.simpleconsoleapp.seedworks.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class ConsoleLoggingSingerSingAspect {
    @Value("${aspect.console.logging.singer.sing}")
    private String expectedStringValue;

    @Pointcut("execution(* sing*(String)) && args(value)")
    public void singerExecution(String value) {
    }

    @Pointcut("bean(better*)")
    public void isBetter() {
    }

    @Before("singerExecution(value) && isBetter()")
    public void beforeLogging(JoinPoint joinpoint, String value) {
        System.out.println(">> [Before] Invoking \"" + joinpoint.getSignature().getName() + "\"");
    }

    @Around("singerExecution(value) && isBetter()")
    public Object aroundLogging(ProceedingJoinPoint joinpoint, String value) throws Throwable {
        if (!value.contains(expectedStringValue))
            return joinpoint.proceed();

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
}
