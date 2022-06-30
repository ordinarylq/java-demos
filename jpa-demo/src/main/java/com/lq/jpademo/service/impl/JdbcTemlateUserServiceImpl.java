package com.lq.jpademo.service.impl;

import com.lq.jpademo.bean.SexEnum;
import com.lq.jpademo.bean.User;
import com.lq.jpademo.service.JdbcTemlateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author qili
 * @create 2022-06-30-20:50
 */
@Service
public class JdbcTemlateUserServiceImpl implements JdbcTemlateUserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 获取映射关系
    private RowMapper<User> getUserMapper() {
        // 使用lambda表达式创建用户映射关系
        return (ResultSet rs, int rownum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUserName(rs.getString("user_name"));
            int sexId = rs.getInt("sex");
            user.setSex(SexEnum.getEnumById(sexId));
            user.setNote(rs.getString("note"));
            return user;
        };
    }


    @Override
    public User getUserById(Long id) {
        // 1. 设置SQL
        String sql = "select id, user_name, sex, note from t_user where id = ?";

        // 2. 参数
        Object[] params = {id};

        // 3. 调用jdbcTemplate的方法执行SQL并返回结果
        return jdbcTemplate.queryForObject(sql, getUserMapper(), params);
    }

    @Override
    public List<User> findUsers(String userName, String note) {
        // 1. 设置SQL
        String sql = "select id, user_name, sex, note from t_user " +
                "where user_name like concat('%', ?, '%') " +
                "and note like concat('%', ?, '%')";

        // 2. 参数
        Object[] params = {userName, note};

        // 3. 调用jdbcTemplate的方法执行并返回结果
        return jdbcTemplate.query(sql, getUserMapper(), params);
    }

    @Override
    public int insertUser(User user) {
        // 1. 设置SQL
        String sql = "insert into t_user(user_name, sex, note) values(?, ?, ?)";

        // 2. 参数
        Object[] params = {user.getUserName(), user.getSex().getId(), user.getNote()};

        // 3. 调用方法并返回结果
        return jdbcTemplate.update(sql,params);
    }

    @Override
    public int updateUser(User user) {
        // 1. 设置SQL
        String sql = "update t_user set user_name = ?, sex = ?, note = ?" +
                " where id = ?";

        // 2. 参数
        Object[] params = {user.getUserName(), user.getSex().getId(), user.getNote(), user.getId()};

        // 3. 调用方法并返回结果
        return jdbcTemplate.update(sql, params);
    }

    @Override
    public int deleteUserById(Long id) {
        // 1. 设置SQL
        String sql = "delete from t_user where id = ?";

        // 2. 参数
        Object[] params = {id};

        // 3. 调用方法并返回结果
        return jdbcTemplate.update(sql, params);
    }
}
