package com.visa.connector.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/*
This interceptor is using AOP to provide additional debug operations before, after, or around method exectuion
If you want to enable this function over method, just @Debug annotation on it.
 */
@Aspect
@Component
public class DebugInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(DebugInterceptor.class);

    /*
    define the pointcut needed to execute advice logic
     */
    @Pointcut("@annotation(Debug)")
    public void debugPointCut() {

    }

    /*
    Process logic added before and after one method's execution
     */
    @Around("debugPointCut()")
    public Object debugAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        // add performance count before method invoke
        long start = System.currentTimeMillis();

        // invoke
        Object result = joinPoint.proceed();
        if (isVoidMethod(joinPoint)) {
            result = null;
        }

        // continue performance count after method invoke
        long interval = System.currentTimeMillis() - start;
        logger.debug("request: {}, result: {}, time cost:{}", joinPoint.getArgs(), result, interval);

        return result;
    }

    /*
    Check if one method being around is a void method
     */
    private boolean isVoidMethod(ProceedingJoinPoint joinPoint) {
        // compatible cast
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        Method method = signature.getMethod();
        Class<?> returnType= method.getReturnType();

        return returnType.equals(void.class);
    }
}
