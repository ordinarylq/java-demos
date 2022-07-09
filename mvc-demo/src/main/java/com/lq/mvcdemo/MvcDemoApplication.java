package com.lq.mvcdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@MapperScan(basePackages = "com.lq.mvcdemo.mapper", annotationClass = Repository.class)
@SpringBootApplication
public class MvcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcDemoApplication.class, args);
    }

}
