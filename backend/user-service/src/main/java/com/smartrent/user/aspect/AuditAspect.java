package com.smartrent.user.aspect;

import com.smartrent.user.annotation.Auditable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class AuditAspect {

    @AfterReturning(value = "@annotation(auditable)", returning = "result")
    public void auditSuccess(JoinPoint jp, Auditable auditable, Object result) {
        String userId = extractHeader("X-User-Id");
        String userRole = extractHeader("X-User-Role");
        String methodName = jp.getSignature().toShortString();
        String args = Arrays.toString(jp.getArgs());

        log.info("AUDIT | timestamp={} | user={} | role={} | action={} | resource={} | method={} | params={} | status=SUCCESS",
                LocalDateTime.now(), userId, userRole, auditable.action(), auditable.resourceType(), methodName, args);
    }

    @AfterThrowing(value = "@annotation(auditable)", throwing = "ex")
    public void auditFailure(JoinPoint jp, Auditable auditable, Exception ex) {
        String userId = extractHeader("X-User-Id");
        String userRole = extractHeader("X-User-Role");
        String methodName = jp.getSignature().toShortString();
        String args = Arrays.toString(jp.getArgs());

        log.warn("AUDIT | timestamp={} | user={} | role={} | action={} | resource={} | method={} | params={} | status=FAILURE | error={}",
                LocalDateTime.now(), userId, userRole, auditable.action(), auditable.resourceType(), methodName, args, ex.getMessage());
    }

    private String extractHeader(String headerName) {
        try {
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs != null) {
                HttpServletRequest request = attrs.getRequest();
                String value = request.getHeader(headerName);
                return value != null ? value : "UNKNOWN";
            }
        } catch (Exception e) {
            log.debug("Could not extract header {}: {}", headerName, e.getMessage());
        }
        return "SYSTEM";
    }
}
