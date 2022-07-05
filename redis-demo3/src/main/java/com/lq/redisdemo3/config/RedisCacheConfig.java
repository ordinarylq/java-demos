package com.lq.redisdemo3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * @author qili
 * @create 2022-07-05-22:16
 */
@Configuration
public class RedisCacheConfig {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean(name = "redisCacheManager")
    public RedisCacheManager initRedisCacheManager() {
        // redis加锁的写入器
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory);

        // 启动Redis缓存的默认配置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();

        // 设置JDK序列化器
        config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                new JdkSerializationRedisSerializer()));

        // 禁用前缀
        config = config.disableKeyPrefix();
        // 设置600s超时
        config = config.entryTtl(Duration.ofMinutes(10));

        // 创建redis缓存管理器并返回
        return new RedisCacheManager(writer, config);



    }
}
