package com.lq.demo;

import com.lq.demo.interceptor.MyInterceptor;
import com.lq.demo.service.HelloService;
import com.lq.demo.service.impl.HelloServiceImpl;
import org.junit.Test;

/**
 * @author qili
 * @create 2022-06-29-20:10
 */
public class TestAOP {
    @Test
    public void test1() {
        HelloService proxyBean = (HelloService) ProxyBean.getProxyBean(new HelloServiceImpl(), new MyInterceptor());
        proxyBean.sayHello("Richard");

    }
}
