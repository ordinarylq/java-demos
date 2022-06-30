package com.lq.demo2.service;

import com.lq.demo2.pojo.User;

/**
 * @author qili
 * @create 2022-06-29-20:40
 */
public interface UserService {
    /**
     * 打印User对象
     * @param user
     */
    void printUser(User user);

    void manyAspects();
}
