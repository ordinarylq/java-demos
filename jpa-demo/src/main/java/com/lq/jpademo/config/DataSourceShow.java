package com.lq.jpademo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author qili
 * @create 2022-06-30-20:37
 */
@Component
public class DataSourceShow implements ApplicationContextAware {
    // IoC容器
    private ApplicationContext applicationContext;

    // Spring容器在启动时通过BeanPostProcessor回调该方法
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        System.out.println("-------------------------------");
        System.out.println(dataSource.getClass().getName());
        System.out.println("-------------------------------");
    }
}
