package com.lq.mybatisdemo.controller;

import com.lq.mybatisdemo.bean.User;
import com.lq.mybatisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qili
 * @create 2022-07-01-20:35
 */
@RestController
@RequestMapping("/mybatis")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public User getUser(Long id) {
        User user = userService.getUser(id);
        System.out.println(user);
        return user;
    }

}
