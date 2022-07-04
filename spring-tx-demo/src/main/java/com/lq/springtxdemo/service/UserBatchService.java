package com.lq.springtxdemo.service;

import com.lq.springtxdemo.bean.User;

import java.util.List;

/**
 * @author qili
 * @create 2022-07-03-9:19
 */
public interface UserBatchService {
    int addUsers(List<User> userList);
}
