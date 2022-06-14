package com.lq.redisdemo01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class RedisDemo01ApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void testRedisConnection() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("Hello", "Redis!");

        String value = ops.get("Hello");
        Assertions.assertEquals("World!", value);
    }

    @Test
    void contextLoads() {

    }

}
