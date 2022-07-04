package com.lq.redis.config.test;

import com.lq.redis.config.RedisConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.nio.charset.StandardCharsets;

/**
 * @author qili
 * @create 2022-07-04-19:28
 */
public class RedisTemplateTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
//        useRedisCallback(redisTemplate);
        useSessionCallback(redisTemplate);
//        redisTemplate.opsForValue().set("hello", "world");
//        redisTemplate.opsForHash().put("hash", "field", "hvalue");
    }

    // SessionCallback
    private static void useSessionCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute((RedisOperations operations) -> {
            operations.opsForValue().set("hello", "world");
            operations.opsForHash().put("hash", "field2", "yayyyyyy");
            return null;
        });
    }

    // RedisCallback
    private static void useRedisCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute((RedisConnection connection) -> {
            connection.set("key1".getBytes(StandardCharsets.UTF_8), "value1".getBytes(StandardCharsets.UTF_8));
            connection.hSet("hash".getBytes(StandardCharsets.UTF_8), "field".getBytes(StandardCharsets.UTF_8),
                    "hvalue".getBytes(StandardCharsets.UTF_8));
            return null;
        });
    }
}
