package com.lq.mybatisdemo.mapper;

import com.lq.mybatisdemo.bean.User;
import org.springframework.stereotype.Repository;

/**
 * @author qili
 * @create 2022-07-01-20:08
 */
@Repository
public interface UserMapper {
    User getUser(Long id);
}
