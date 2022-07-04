package com.lq.springtxdemo.mapper;

import com.lq.springtxdemo.bean.User;
import org.springframework.stereotype.Repository;

/**
 * @author qili
 * @create 2022-07-03-8:32
 */
@Repository
public interface UserMapper {
    User selectUser(Long id);
    int insertUser(User user);
}
