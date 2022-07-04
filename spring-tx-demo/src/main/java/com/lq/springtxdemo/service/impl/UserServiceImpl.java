package com.lq.springtxdemo.service.impl;

import com.lq.springtxdemo.bean.User;
import com.lq.springtxdemo.mapper.UserMapper;
import com.lq.springtxdemo.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author qili
 * @create 2022-07-03-8:40
 */
@Service
public class UserServiceImpl implements UserService, ApplicationContextAware {
    @Autowired
    private UserMapper userMapper;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1)
    public User findUserById(Long id) {
        return userMapper.selectUser(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1, propagation = Propagation.REQUIRES_NEW)
    public Integer addUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int addUsers(List<User> userList) {
        int count = 0;
        // 从IoC容器中获取代理对象
        UserService userService = this.applicationContext.getBean(UserService.class);
        for (User user : userList) {
            // 如果直接调用自己类中的方法，产生自调用问题
            // 通过使用代理对象调用方法，此时会织入Spring数据库事务流程中
            count += userService.addUser(user);
        }
        return count;
    }
}
