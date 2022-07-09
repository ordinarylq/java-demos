package com.lq.mvcdemo.controller;

import com.lq.mvcdemo.bean.User;
import com.lq.mvcdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;

/**
 * @author qili
 * @create 2022-07-09-8:10
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/details")
    public ModelAndView details(Long id) {
        User user = userService.findUserById(id);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("user", user);
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        modelAndView.setView(jsonView);

        return modelAndView;
    }

    @RequestMapping("/table")
    public ModelAndView table() {
        ModelAndView mv = new ModelAndView();
        List<User> users = userService.findUsers(null, null);
        mv.addObject("users", users);
        mv.setViewName("table");
        return mv;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<User> list(
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "note", required = false) String note
    ) {
        return userService.findUsers(userName, note);
    }

    @PostMapping("/add")
    @ResponseBody
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }
}
