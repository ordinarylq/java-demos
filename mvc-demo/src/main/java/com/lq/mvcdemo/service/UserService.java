package com.lq.mvcdemo.service;

import com.lq.mvcdemo.bean.User;

import java.util.List;

/**
 * @author qili
 * @create 2022-07-09-8:24
 */
public interface UserService {
    /**
     * 根据id找到用户
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * 根据userName, note查找特定用户
     * @param userName
     * @param note
     * @return
     */
    List<User> findUsers(String userName, String note);

    /**
     * 添加用户
     * @param user
     * @return
     */
    User addUser(User user);
}
