package com.smartrent.property.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PerformanceAspect {

    private static final long THRESHOLD_MS = 500;

    @Around("execution(* com.smartrent.property.service.impl.*.*(..))") // here we use around 3l4an n7sb el time bta3 el
                                                                        // method before w after el execution
    public Object monitorPerformance(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed(); // hena bnbd2 el method
        long elapsed = System.currentTimeMillis() - start; // bn7sb el time

        String methodName = pjp.getSignature().toShortString();

        if (elapsed > THRESHOLD_MS) { // if the method took more than 500ms we log it as a warning
            log.warn("SLOW METHOD: {} took {}ms (threshold: {}ms)", methodName, elapsed, THRESHOLD_MS);
        } else {
            log.debug("{} completed in {}ms", methodName, elapsed);
        }

        return result;
    }
}
