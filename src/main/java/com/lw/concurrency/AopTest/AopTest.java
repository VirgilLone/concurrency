package com.lw.concurrency.AopTest;

import org.aspectj.lang.JoinPoint;
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
    public void doSomething(){
        System.out.println("此处为一个切点。。。");
    }

    @Before("doSomething()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request= attributes.getRequest();

        log.info("ip={}",request.getRequestURI());
        log.info("url={}",request.getRequestURL());
        log.info("method={}",request.getMethod());

        log.info("类方法={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        log.info("args={}",joinPoint.getArgs());

    }

    @Before("execution(public * com.lw.concurrency.controller.TestController.*(..))")
    public void log(){
        System.out.println("hhhahah");
    }

}
