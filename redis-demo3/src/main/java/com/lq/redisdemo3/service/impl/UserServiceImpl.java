package com.lq.redisdemo3.service.impl;

import com.lq.redisdemo3.bean.User;
import com.lq.redisdemo3.mapper.UserMapper;
import com.lq.redisdemo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author qili
 * @create 2022-07-05-20:23
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    // 获取id, 取参数id缓存用户
    @Cacheable(value = "redisCache", key = "'redis_user_' + #id")
    public User findUserById(Long id) {
        return userMapper.selectUserById(id);
    }

    @Override
    @Transactional
    // 插入用户，最后MyBatis会回写id, 取结果id缓存用户
    @CachePut(value = "redisCache", key = "'redis_user_' + #result.id")
    public User saveUser(User user) {
        userMapper.insertUser(user);
        return user;
    }

    @Override
    @Transactional
    @CachePut(value = "redisCache", condition = "#result != 'null'",key = "'redis_user_' + #id")
    public User editUserName(Long id, String userName) {
        // 此处调用了getUser方法，则该方法的缓存注解失效
        // 因此此处还会执行SQL，查询数据库最新数据
        User user = this.findUserById(id);
        if(user == null) {
            return null;
        }

        user.setUserName(userName);
        userMapper.updateUser(user);
        return user;
    }

    // 命中率低， 因此不采用缓存机制
    @Override
    @Transactional
    public List<User> findUsers(String userName, String note) {
        return userMapper.selectUsers(userName, note);
    }

    // 从缓存中移除
    @Override
    @Transactional
    @CacheEvict(value = "redisCache", key = "'redis_user_' + #id", beforeInvocation = false)
    public int removeUserById(Long id) {
        return userMapper.deleteUserById(id);
    }
}
