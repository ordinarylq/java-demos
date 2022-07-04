package com.lq.redisdemo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RedisDemo2Application {
    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void init() {
        initRedisTemplate();
    }

    private void initRedisTemplate() {
        RedisSerializer stringSerializer = this.redisTemplate.getStringSerializer();
        this.redisTemplate.setKeySerializer(stringSerializer);
        this.redisTemplate.setHashKeySerializer(stringSerializer);
        this.redisTemplate.setHashValueSerializer(stringSerializer);
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisDemo2Application.class, args);
    }

}
