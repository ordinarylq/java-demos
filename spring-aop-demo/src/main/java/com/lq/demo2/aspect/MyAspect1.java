package com.lq.demo2.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

/**
 * @author qili
 * @create 2022-06-30-19:52
 */
@Order(3)
@Aspect
public class MyAspect1 {

    @Pointcut(value = "execution(* com.lq.demo2.service.impl.UserServiceImpl.manyAspects(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void before() {
        System.out.println("before1...");
    }

    @After(value = "pointCut()")
    public void after() {
        System.out.println("after1...");
    }

    @AfterReturning(value = "pointCut()")
    public void afterReturning() {
        System.out.println("afterReturning1...");
    }
}
