package com.lq.jpademo.controller;

import com.lq.jpademo.bean.User;
import com.lq.jpademo.dao.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qili
 * @create 2022-06-30-21:28
 */
@RestController
@RequestMapping("/jpa")
public class JpaController {
    @Autowired
    private JpaUserRepository jpaUserRepository;

    @GetMapping("/getUser")
    public User getUser(Long id) {
        return jpaUserRepository.findById(id).get();
    }

    @GetMapping("/getUserList")
    public List<User> getUserList(String userName, String note) {
        return jpaUserRepository.findUsers(userName, note);
    }

    @GetMapping("/getUserById")
    public User getUserById(Long id) {
        return jpaUserRepository.getUserById(id);
    }

    @GetMapping("/findByUserNameLike")
    public List<User> findByUserNameLike(String userName) {
        return jpaUserRepository.findByUserNameLike("%" + userName + "%");
    }

    @GetMapping("/findByUserNameLikeOrNoteLike")
    public List<User> findByUserNameLikeOrNoteLike(String userName, String note) {
        String userNameLike = "%" + userName + "%";
        String noteLike = "%" + note + "%";

        return jpaUserRepository.findByUserNameLikeOrNoteLike(userNameLike, noteLike);

    }
}
