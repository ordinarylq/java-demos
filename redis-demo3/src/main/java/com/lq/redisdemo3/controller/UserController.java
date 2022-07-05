package com.lq.redisdemo3.controller;

import com.lq.redisdemo3.bean.User;
import com.lq.redisdemo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author qili
 * @create 2022-07-05-20:37
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public User getUser(Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/saveUser")
    public User saveUser(String userName, String note) {
        return userService.saveUser(new User(null, userName, note));
    }

    @GetMapping("/getUsers")
    public List<User> getUsers(String userName, String note) {
        return userService.findUsers(userName, note);
    }

    @GetMapping("/removeUser")
    public Map<String, Object> removeUser(Long id) {
        int result = userService.removeUserById(id);
        String msg = (result == 1) ? "删除成功" : "删除失败";
        HashMap<String, Object> map = new HashMap<>();
        map.put("success", result == 1);
        map.put("msg", msg);
        return map;

    }
}
