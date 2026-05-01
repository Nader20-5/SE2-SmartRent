package com.smartrent.rental.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.smartrent.rental.controller.*.*(..))")
    public Object logControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        // TODO: log method name, args, duration, status
        return pjp.proceed();
    }
}
