package com.lq.mvcdemo.mapper;

import com.lq.mvcdemo.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qili
 * @create 2022-07-09-8:19
 */
@Repository
public interface UserMapper {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User selectUserById(Long id);

    /**
     * 根据userName, note查询用户
     * @param userName 用户名
     * @param note 备注
     * @return
     */
    List<User> selectUsers(@Param("userName") String userName,
                           @Param("note") String note);

    /**
     * 插入用户
     * @param user
     * @return
     */
    int insertUser(User user);
}
