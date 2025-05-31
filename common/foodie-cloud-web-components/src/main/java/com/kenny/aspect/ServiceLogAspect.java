package com.kenny.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLogAspect {

    public static final Logger log =
            LoggerFactory.getLogger(ServiceLogAspect.class);

    /**
     * AOP Notifications:
     * 1. Before Advice: Executes before the method is called
     * 2. After Advice: Executes after the method is called normally
     * 3. Around Advice: Executes before and after the method is called
     * 4. After Throwing Advice: Executes if an exception occurs during the method call
     * 5. After Finally Advice: Executes after the method call, regardless of the outcome
     */

    /**
     * Aspect Expression:
     * @Around("execution(* com.kenny.service.impl..*.*(..))")
     * execution represents the main body of the expression to be executed
     * First * represents the method return type, * represents all types
     * Second part is the package name where the class monitored by AOP is located
     * Third part .. represents all class methods in the package and its sub-packages
     * Fourth part * represents the class name, * represents all classes
     * Fifth part *(..) represents the method name in the class, (..) indicates any parameters in the method
     *
     * ````````````````
     *      * Pointcut expression explanation for:
     * @Around("execution(* com.kenny..*.service.impl..*.*(..))")
     *
     * - execution(* com.kenny..*.service.impl..*.*(..))
     *   - *: matches any return type
     *   - com.kenny..*: matches any class in any subpackage of com.kenny
     *   - service.impl..*: matches any class in the service.impl package or its subpackages
     *   - *: matches any class name
     *   - * (..): matches any method name with any parameters
     * This pointcut intercepts all methods in any class under com.kenny's service.impl package and its subpackages.
     * ````````````````
     * 
     * 
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.kenny..*.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("====== Start executing {}.{} ======",
                        joinPoint.getTarget().getClass(),
                        joinPoint.getSignature().getName());

        // Record start time
        long begin = System.currentTimeMillis();

        // Execute target service
        Object result = joinPoint.proceed();

        // Record end time
        long end = System.currentTimeMillis();
        long takeTime = end - begin;

        if (takeTime > 3000) {
            log.error("====== Execution completed, time taken: {} milliseconds ======", takeTime);
        } else if (takeTime > 2000) {
            log.warn("====== Execution completed, time taken: {} milliseconds ======", takeTime);
        } else {
            log.info("====== Execution completed, time taken: {} milliseconds ======", takeTime);
        }

        return result;
    }

}
