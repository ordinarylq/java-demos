package com.lq.demo2.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

/**
 * @author qili
 * @create 2022-06-30-19:52
 */
@Order(1)
@Aspect
public class MyAspect2 {

    @Pointcut(value = "execution(* com.lq.demo2.service.impl.UserServiceImpl.manyAspects(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void before() {
        System.out.println("before2...");
    }

    @After(value = "pointCut()")
    public void after() {
        System.out.println("after2...");
    }

    @AfterReturning(value = "pointCut()")
    public void afterReturning() {
        System.out.println("afterReturning2...");
    }
}
