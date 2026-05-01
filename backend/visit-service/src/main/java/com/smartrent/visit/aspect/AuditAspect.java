package com.smartrent.visit.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {

    @AfterReturning("@annotation(com.smartrent.visit.annotation.Auditable)")
    public void auditAction(JoinPoint jp) {
        // TODO: log who did what on which resource
    }
}
