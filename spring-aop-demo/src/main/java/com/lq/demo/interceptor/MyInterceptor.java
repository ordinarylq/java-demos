package com.lq.demo.interceptor;

import com.lq.demo.invocation.Invocation;

import java.lang.reflect.InvocationTargetException;

/**
 * @author qili
 * @create 2022-06-29-19:33
 */
public class MyInterceptor implements Interceptor{
    @Override
    public boolean before() {
        System.out.println("before...");
        return true;
    }

    @Override
    public boolean after() {
        System.out.println("after...");
        return true;
    }

    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("around-before...");
        Object proceed = invocation.proceed();
        System.out.println("arouund-after...");
        return proceed;
    }

    @Override
    public void afterReturning() {
        System.out.println("afterReturning...");
    }

    @Override
    public void afterThrowing() {
        System.out.println("afterThrowing...");
    }

    @Override
    public boolean useAround() {
        return true;
    }
}
