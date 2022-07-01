package com.lq.mybatisdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author qili
 * @create 2022-07-01-20:09
 */
@MapperScan(basePackages = "com.lq.mybatisdemo.mapper")
@SpringBootApplication
public class MyBatisDemoAplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBatisDemoAplication.class, args);
    }
}
