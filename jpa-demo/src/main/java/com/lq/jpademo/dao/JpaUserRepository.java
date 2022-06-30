package com.lq.jpademo.dao;

import com.lq.jpademo.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author qili
 * @create 2022-06-30-21:28
 */
public interface JpaUserRepository extends JpaRepository<User, Long> {

    @Query("from user where user_name like concat('%', ?1, '%')" +
            " and note like concat('%',?2, '%')")
    List<User> findUsers(String userName, String note);

    /**
     * 按照名称模糊查询
     * @param userName
     * @return
     */
    List<User> findByUserNameLike(String userName);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 根据名称或备注进行模糊查询
     * @param userName
     * @param note
     * @return
     */
    List<User> findByUserNameLikeOrNoteLike(String userName, String note);
}
