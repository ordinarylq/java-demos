package com.lq.demo2.validator.impl;

import com.lq.demo2.pojo.User;
import com.lq.demo2.validator.UserValidator;

/**
 * @author qili
 * @create 2022-06-30-18:59
 */
public class UserValidatorImpl implements UserValidator {
    @Override
    public boolean validate(User user) {
        System.out.println("引入新的接口：" + UserValidator.class.getSimpleName());
        return user != null;
    }
}
