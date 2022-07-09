package com.lq.mvcdemo.service.impl;

import com.lq.mvcdemo.bean.User;
import com.lq.mvcdemo.mapper.UserMapper;
import com.lq.mvcdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qili
 * @create 2022-07-09-8:24
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(Long id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public List<User> findUsers(String userName, String note) {
        return userMapper.selectUsers(userName, note);
    }

    @Override
    public User addUser(User user) {
        userMapper.insertUser(user);
        return user;
    }
}
