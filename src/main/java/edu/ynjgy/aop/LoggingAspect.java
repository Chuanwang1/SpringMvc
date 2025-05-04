package edu.ynjgy.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;


/**
 * @author LCW69
 */

@Aspect
@Component
public class LoggingAspect {
     Logger logger = (Logger) LoggerFactory.getLogger(LoggingAspect.class);
//    private final Log log = (Log) LoggerFactory.getLogger(LoggingAspect.class);
    //  定义切点
    @Pointcut("execution(* edu.ynjgy.service.*.*(..))")
    public void servicePointcut() {

    }

    // 前置通知
    @Before("servicePointcut()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("开始执行方法: " + methodName);
    }

    // 后置通知
    @After("servicePointcut()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("方法执行结束: " + methodName);
    }

    // 环绕通知
    @Around("servicePointcut()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        long startTime = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            logger.info("方法 " + methodName + " 执行时间: " + (endTime - startTime) + "ms");
            return result;
        } catch (Exception e) {
            logger.error("方法 " + methodName + " 执行异常: " + e.getMessage());
            throw e;
        }
    }

    // 异常通知
    @AfterThrowing(pointcut = "servicePointcut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        String methodName = joinPoint.getSignature().getName();
        logger.error("方法 " + methodName + " 执行异常: " + e.getMessage());
    }
}
