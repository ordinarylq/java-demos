package com.lq.mvcdemo.controller;

import com.lq.mvcdemo.bean.User;
import com.lq.mvcdemo.service.UserService;
import com.lq.mvcdemo.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/converter")
    @ResponseBody
    public User getUserByCustomConverter(User user) {
        return user;
    }


    @GetMapping("/list")
    @ResponseBody
    public List<User> list(List<User> userList) {
        return userList;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 1. 绑定自定义的validator
        binder.setValidator(new UserValidator());

        // 2. 设置java.util.Date日期参数格式，参数就不再需要@DateTimeFormat注解
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
    }

    @GetMapping("/validator")
    @ResponseBody
    public Map<String, Object> validator(
            @Valid User user, Errors errors, Date date) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("user", user);
        map.put("date", date);

        // 判断是否存在错误
        if(errors.hasErrors()) {
            List<ObjectError> errorList = errors.getAllErrors();
            for (ObjectError error : errorList) {
                if(error instanceof FieldError) {
                    FieldError fe = (FieldError) error;
                    map.put(fe.getField(), fe.getDefaultMessage());
                } else {
                    map.put(error.getObjectName(), error.getDefaultMessage());
                }
            }
        }
        return map;
    }
}
