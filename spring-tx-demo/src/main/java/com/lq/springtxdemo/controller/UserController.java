package com.lq.springtxdemo.controller;

import com.lq.springtxdemo.bean.User;
import com.lq.springtxdemo.service.UserBatchService;
import com.lq.springtxdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qili
 * @create 2022-07-03-8:42
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserBatchService userBatchService;
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("/")
    public Map<String, Object> addUser(@RequestBody User user) {
        System.out.println(user);
        Integer res = userService.addUser(user);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("success", res == 1);
        map.put("user", user);
        return map;
    }

    @PostMapping("/batch")
    public Map<String, Object> addBatchUsers(@RequestBody List<User> userList) {
        //int res = userBatchService.addUsers(userList);
        int res = userService.addUsers(userList);
        HashMap<String, Object> map = new HashMap<>();
        map.put("success", res > 0);
        map.put("users", userList);
        return map;
    }
}
