package com.smartrent.visit.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {

    private static final long THRESHOLD_MS = 500;

    @Around("execution(* com.smartrent.visit.service.impl.*.*(..))")
    public Object monitorPerformance(ProceedingJoinPoint pjp) throws Throwable {
        // TODO: measure execution time, warn if > THRESHOLD_MS
        return pjp.proceed();
    }
}
