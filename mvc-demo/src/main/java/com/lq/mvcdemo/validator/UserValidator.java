package com.lq.mvcdemo.validator;

import com.lq.mvcdemo.bean.User;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author qili
 * @create 2022-07-12-20:58
 */
public class UserValidator implements Validator {
    // 该验证器只支持验证User类型
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(target == null) {
            // 如果对象为空, 则直接在参数处报错，不进入控制器方法
            errors.rejectValue("", null, "用户不能为空");
            return;
        }
        User user = (User) target;
        if(!StringUtils.hasLength(user.getUserName())) {
            // 增加错误，可以进入控制器方法
            errors.rejectValue("userName", null, "用户名不能为空");
        }
    }
}
