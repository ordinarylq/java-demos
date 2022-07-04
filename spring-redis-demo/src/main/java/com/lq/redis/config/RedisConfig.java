package com.lq.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @author qili
 * @create 2022-07-04-19:16
 */
@Configuration
public class RedisConfig {
    private RedisConnectionFactory connectionFactory;

    @Bean(name = "RedisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory() {
        if(this.connectionFactory != null) {
            return this.connectionFactory;
        }
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大空闲数
        poolConfig.setMaxIdle(30);
        // 最大连接数
        poolConfig.setMaxTotal(50);
        // 最大等待的毫秒数
        poolConfig.setMaxWait(Duration.ofMillis(2000));

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        RedisStandaloneConfiguration standaloneConfiguration = jedisConnectionFactory.getStandaloneConfiguration();
        assert standaloneConfiguration != null;
        standaloneConfiguration.setHostName("192.168.145.128");
        standaloneConfiguration.setPort(6379);
        this.connectionFactory = jedisConnectionFactory;
        return jedisConnectionFactory;
    }

//    @Bean(name = "RedisTemplate")
//    public RedisTemplate<Object, Object> initRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        return redisTemplate;
//    }
    @Bean(name = "RedisTemplate")
    public RedisTemplate<Object, Object> initRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        // RedisTemplate会自动初始化StringRedisSerializer
        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        // 设置字符串序列化器，這樣Spring就會把Redis的key當作字符串處理了。
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        return redisTemplate;
    }

}
