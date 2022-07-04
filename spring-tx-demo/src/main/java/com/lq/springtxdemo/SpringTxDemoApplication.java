package com.lq.springtxdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;

@MapperScan(basePackages = "com.lq.springtxdemo.mapper", annotationClass = Repository.class)
@SpringBootApplication
public class SpringTxDemoApplication {
    @Autowired
    PlatformTransactionManager transactionManager;

    public static void main(String[] args) {
        SpringApplication.run(SpringTxDemoApplication.class, args);
    }

    @PostConstruct
    public void viewTransactionManager() {
        System.out.println("txManager=" + transactionManager.getClass().getName());
    }

}
