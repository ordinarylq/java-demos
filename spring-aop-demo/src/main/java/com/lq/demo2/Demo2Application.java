package com.lq.demo2;

import com.lq.demo2.aspect.MyAspect;
import com.lq.demo2.aspect.MyAspect1;
import com.lq.demo2.aspect.MyAspect2;
import com.lq.demo2.aspect.MyAspect3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author qili
 * @create 2022-06-29-21:02
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class Demo2Application {
    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }

    @Bean("myAspect")
    public MyAspect initMyAspect() {
        return new MyAspect();
    }

    @Bean("myAspect1")
    public MyAspect1 initMyAspect1() {
        return new MyAspect1();
    }
    @Bean("myAspect2")
    public MyAspect2 initMyAspect2() {
        return new MyAspect2();
    }
    @Bean("myAspect3")
    public MyAspect3 initMyAspect3() {
        return new MyAspect3();
    }
}
