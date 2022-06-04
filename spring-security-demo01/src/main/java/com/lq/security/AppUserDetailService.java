package com.lq.security;

import com.lq.security.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qili
 * @create 2022-06-04-11:38
 */
@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 创建SQL语句，根据用户名从数据库中查找出用户对象
        String sql = "SELECT id, loginacct \"loginAcct\", userPswd \"loginPswd\", username, email FROM t_admin WHERE loginacct = ?";

        // 2. 使用jdbcTemplate查询结果
        List<Admin> admins = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Admin.class), username);

        // 3. 获取用户名密码数据
        Admin admin = admins.get(0);
        String password = admin.getLoginPswd();
        // 4. 创建权限列表
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_外门弟子"));

        // 5. 创建UserDetails并返回
        return new org.springframework.security.core.userdetails.User(username, password, authorities);
    }
}
