package com.lq.demo2.aspect;

import com.lq.demo2.pojo.User;
import com.lq.demo2.validator.UserValidator;
import com.lq.demo2.validator.impl.UserValidatorImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @author qili
 * @create 2022-06-29-20:44
 */
@Aspect
public class MyAspect {

    @DeclareParents(
            value = "com.lq.demo2.service.impl.UserServiceImpl",
            defaultImpl = UserValidatorImpl.class
    )
    public UserValidator userValidator;

    @Pointcut("execution(* com.lq.demo2.service.impl.UserServiceImpl.printUser(..))")
    public void pointCut() {

    }

    @Before(value = "pointCut() && args(user)")
    public void before(JoinPoint joinPoint, User user) {
        System.out.println("In before, user = " + user);
        System.out.println("通过JoinPoint获取参数：" + Arrays.asList(joinPoint.getArgs()));
        System.out.println("before...");
    }

    @Around(value = "pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("around before...");
        jp.proceed();
        System.out.println("around after...");
    }

    @After(value = "pointCut()")
    public void after() {
        System.out.println("after...");
    }

    @AfterReturning(value = "pointCut()")
    public void afterReturning() {
        System.out.println("afterReturning");
    }

    @AfterThrowing(value = "pointCut()")
    public void afterThrowing() {
        System.out.println("afterThrowing");
    }
}
