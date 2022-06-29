package com.lq.demo;

import com.lq.demo.interceptor.Interceptor;
import com.lq.demo.invocation.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author qili
 * @create 2022-06-29-19:38
 */
public class ProxyBean implements InvocationHandler {
    private Object target = null;

    private Interceptor interceptor = null;

    /**
     * 生成target的一个代理对象
     * @param target
     * @param interceptor
     * @return
     */
    public static Object getProxyBean(Object target, Interceptor interceptor) {
        ProxyBean proxyBean = new ProxyBean();
        proxyBean.setTarget(target);
        proxyBean.setInterceptor(interceptor);
        return Proxy.newProxyInstance(
                ProxyBean.class.getClassLoader(),
                target.getClass().getInterfaces(),
                proxyBean);

    }

    // 当代理对象调用sayHello方法时，会调用invoke()方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Invocation invocation = new Invocation(args, method, this.target);
        Object returnValue = null;
        boolean exceptionFlag = false;
        // 方法前
        try {
            if(this.interceptor.before()) {
                // 环绕
                returnValue =  this.interceptor.around(invocation);
            } else {
                returnValue = method.invoke(target, args);
            }
        } catch (Exception e) {
            exceptionFlag = true;
        }
        // 方法后
        this.interceptor.after();

        if(exceptionFlag) {
            // 异常
            this.interceptor.afterThrowing();
        } else {
            // 返回
            this.interceptor.afterReturning();
            return  returnValue;
        }

        return null;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public void setInterceptor(Interceptor interceptor) {
        this.interceptor = interceptor;
    }
}
