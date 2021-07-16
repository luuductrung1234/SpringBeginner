package com.learn.simpleconsoleapp.seedworks.advices;

import org.aspectj.lang.JoinPoint;

public class AuditAdvice {
    public void simpleBeforeAdvice(JoinPoint joinPoint) {
        System.out.println("Executing: "
                + joinPoint.getSignature().getDeclaringTypeName() + " "
                + joinPoint.getSignature().getName());
    }
}
