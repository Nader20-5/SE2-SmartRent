package com.smartrent.property.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class AuditAspect {

    @AfterReturning("@annotation(com.smartrent.property.annotation.Auditable)")
    public void auditAction(JoinPoint jp) {
        String methodName = jp.getSignature().toShortString();
        String args = Arrays.toString(jp.getArgs());

        // Extract the userId from the first argument if present (controller convention)
        Object userId = jp.getArgs().length > 0 ? jp.getArgs()[0] : "SYSTEM";

        log.info("AUDIT | user={} | action={} | params={}", userId, methodName, args);
    }
}
