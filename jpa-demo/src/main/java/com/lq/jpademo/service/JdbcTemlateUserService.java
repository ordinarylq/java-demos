package com.lq.jpademo.service;

import com.lq.jpademo.bean.User;

import java.util.List;

/**
 * @author qili
 * @create 2022-06-30-20:48
 */
public interface JdbcTemlateUserService {

    User getUserById(Long id);

    List<User> findUsers(String userName, String note);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUserById(Long id);
}
