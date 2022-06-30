package com.lq.demo2.controller;

import com.lq.demo2.pojo.User;
import com.lq.demo2.service.UserService;
import com.lq.demo2.service.impl.UserServiceImpl;
import com.lq.demo2.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qili
 * @create 2022-06-29-21:00
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService = null;

    @GetMapping("/print")
    @ResponseBody
    public User printUser(Long id, String username, String note) {
        User user = new User(id, username, note);
        //user = null;
        userService.printUser(user);
        return user;
    }

    @GetMapping("/validate/print")
    @ResponseBody
    public User validateAndPrintUser(User user) {
        //user = null;
        UserValidator userValidator = (UserValidator) userService;
        if(userValidator.validate(user)) {
            userService.printUser(user);
        }
        return user;
    }

    @GetMapping("/manyAspects")
    @ResponseBody
    public String manyAspects() {
        userService.manyAspects();
        return "Test many aspects!";
    }
}
