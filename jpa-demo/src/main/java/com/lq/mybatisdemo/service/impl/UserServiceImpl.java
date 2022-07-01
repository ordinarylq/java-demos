package com.lq.mybatisdemo.service.impl;

import com.lq.mybatisdemo.bean.User;
import com.lq.mybatisdemo.mapper.UserMapper;
import com.lq.mybatisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qili
 * @create 2022-07-01-20:34
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(Long id) {
        return userMapper.getUser(id);
    }
}
