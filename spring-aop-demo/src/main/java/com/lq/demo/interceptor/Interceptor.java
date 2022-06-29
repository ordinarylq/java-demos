package com.lq.demo.interceptor;

import com.lq.demo.invocation.Invocation;

import java.lang.reflect.InvocationTargetException;

/**
 * @author qili
 * @create 2022-06-29-19:28
 */
public interface Interceptor {
    /**
     * 方法调用前
     */
    boolean before();

    /**
     * 方法调用后
     */
    boolean after();

    /**
     * 环绕通知
     * @param invocation
     * @return
     */
    Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException;

    /**
     * 返回通知
     */
    void afterReturning();

    /**
     * 异常通知
     */
    void afterThrowing();

    /**
     * 是否使用环绕通知
     * @return
     */
    boolean useAround();
}
