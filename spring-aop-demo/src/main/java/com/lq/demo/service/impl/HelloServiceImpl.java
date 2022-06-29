package com.lq.demo.service.impl;

import com.lq.demo.service.HelloService;

/**
 * @author qili
 * @create 2022-06-29-19:26
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        if(name == null || "".equals(name.trim())) {
            throw new RuntimeException("name为null或空字符串");
        }
        System.out.println("Hello, " + name + "!");
    }
}
