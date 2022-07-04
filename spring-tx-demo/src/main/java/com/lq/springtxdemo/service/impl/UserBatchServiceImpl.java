package com.lq.springtxdemo.service.impl;

import com.lq.springtxdemo.bean.User;
import com.lq.springtxdemo.mapper.UserMapper;
import com.lq.springtxdemo.service.UserBatchService;
import com.lq.springtxdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author qili
 * @create 2022-07-03-9:20
 */
@Service
public class UserBatchServiceImpl implements UserBatchService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int addUsers(List<User> userList) {
        int count = 0;
        for (User user : userList) {
            // REQUIRED: 当前方法存在事务，则子方法使用当前事务；当前方法不存在事务，则
            count += userService.addUser(user);
        }
        return count;
    }
}
