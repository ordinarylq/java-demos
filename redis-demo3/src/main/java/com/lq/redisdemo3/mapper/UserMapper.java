package com.lq.redisdemo3.mapper;

import com.lq.redisdemo3.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qili
 * @create 2022-07-05-20:12
 */
@Repository
public interface UserMapper {

    User selectUserById(Long id);

    int insertUser(User user);

    int updateUser(User user);

    List<User> selectUsers(
            @Param("userName") String userName,
            @Param("note") String note);

    int deleteUserById(Long id);
}
