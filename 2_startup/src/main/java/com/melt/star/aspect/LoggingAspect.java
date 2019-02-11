package com.melt.star.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
    // "Aspect-Oriented pROGRAMMING", AOP - сквозная функциональность
    // Advice, PointCut, JoinPoint

    @Pointcut("@annotation(Loggable)") // собирает все точки @Loggable
    public void executeLogging() {
    }

    // Sample #1
    //@Before("executeLogging()")
    public void LogMethodCall(JoinPoint joinPoint) {
        StringBuilder msg = new StringBuilder("Method: ");
        msg.append(joinPoint.getSignature().getName()).append(" ! ");
        Arrays.stream(joinPoint.getArgs()).forEach( arg -> {
            msg.append("args: ").append(arg).append(" ! ");
        });
        log.info(msg.toString());
    }

    // Sample #2
    //@AfterReturning(pointcut = "executeLogging()", returning = "returnValue")
    public void logMethodCall(JoinPoint joinPoint, Object returnValue) {
        StringBuilder msg = new StringBuilder("Method: ");
        msg.append(joinPoint.getSignature().getName()).append(" # ");
        msg.append("return: ").append(returnValue);

        log.info(msg.toString());
    }

    // Sample #3
    @Around("executeLogging()")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();
        StringBuilder msg = new StringBuilder("Method: ");
        msg.append(joinPoint.getSignature().getName()).append(" $ ");

        long deltaTime = System.currentTimeMillis() - start;
        msg.append("time: ").append(deltaTime).append("ms");

        log.info(msg.toString());

        return result;
    }
}
