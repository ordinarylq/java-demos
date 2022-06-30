package com.lq.demo2.service.impl;

import com.lq.demo2.pojo.User;
import com.lq.demo2.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author qili
 * @create 2022-06-29-20:41
 */
@Service
public class UserServiceImpl implements UserService {
//public class UserServiceImpl implements UserService {

    @Override
    public void printUser(User user) {
        if(user == null) {
            throw new RuntimeException("检查用户参数是否为空");
        }
        System.out.println(user);
    }

    @Override
    public void manyAspects() {
        System.out.println("测试多个切面顺序！");
    }
}
