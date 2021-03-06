package com.lw.concurrency.AopTest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by WYluo on 2018/3/23.
 */
@Aspect
@Component
public class AopTest {
    private Logger log= LoggerFactory.getLogger(AopTest.class);

    @Pointcut("execution(public * com.lw.concurrency.controller.TestController.*(..))")
    public void log_point(){
        System.out.println("此处为一个切点。。。");
    }

    @Before("log_point()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request= attributes.getRequest();

        log.info("ip={}",request.getRemoteAddr());
        log.info("url={}",request.getRequestURL());
        log.info("方法完整路径：{}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        log.info("args={}",joinPoint.getArgs());

    }

    @AfterReturning(returning = "obj",pointcut = "log_point()")
    public void doAfterReturning(Object obj){
        log.info("respongse:{}",obj);
    }

    @Around("log_point()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        Signature signature = joinPoint.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();

        long costTime = endTime - beginTime;
        if (costTime < 500) {
            log.info(className + "." + methodName + ", cost: " + costTime + "ms.");
        } else {
            log.warn(className + "." + methodName + ", cost: " + costTime + "ms.");
        }

        return proceed;

    }

}
