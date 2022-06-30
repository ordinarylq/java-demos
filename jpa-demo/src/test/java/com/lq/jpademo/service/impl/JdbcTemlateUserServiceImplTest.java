package com.lq.jpademo.service.impl;

import com.lq.jpademo.bean.SexEnum;
import com.lq.jpademo.bean.User;
import com.lq.jpademo.service.JdbcTemlateUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author qili
 * @create 2022-06-30-21:16
 */
@SpringBootTest
class JdbcTemlateUserServiceImplTest {

    @Autowired
    private JdbcTemlateUserService userService;

    @Test
    void getUserById() {
        User user = userService.getUserById(1L);
        System.out.println(user);
    }

    @Test
    void findUsers() {
        List<User> users = userService.findUsers("m", "猫");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    void insertUser() {
        User user = new User(null, "Jerry", SexEnum.MALE, "杰瑞鼠");
        int res = userService.insertUser(user);
        System.out.println(res);
    }

    @Test
    void updateUser() {
        User user = new User(1L, "Tom222", SexEnum.MALE, "汤姆222猫");
        int res = userService.updateUser(user);
        System.out.println(res);
    }

    @Test
    void deleteUserById() {
        int res = userService.deleteUserById(1L);
        System.out.println(res);
    }
}