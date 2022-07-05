package com.lq.redisdemo3.service;

import com.lq.redisdemo3.bean.User;

import java.util.List;

/**
 * @author qili
 * @create 2022-07-05-20:21
 */
public interface UserService {
    User findUserById(Long id);

    User saveUser(User user);

    User editUserName(Long id, String userName);

    List<User> findUsers(String userName, String note);

    int removeUserById(Long id);
}
