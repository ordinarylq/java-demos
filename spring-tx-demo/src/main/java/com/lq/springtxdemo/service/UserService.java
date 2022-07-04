package com.lq.springtxdemo.service;

import com.lq.springtxdemo.bean.User;

import java.util.List;

/**
 * @author qili
 * @create 2022-07-03-8:40
 */
public interface UserService {
    User findUserById(Long id);

    Integer addUser(User user);

    int addUsers(List<User> userList);
}
