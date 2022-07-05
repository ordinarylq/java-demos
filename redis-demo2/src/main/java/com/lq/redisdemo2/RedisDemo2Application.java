package com.lq.redisdemo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RedisDemo2Application {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private MessageListener messageListener;

    private ThreadPoolTaskScheduler taskScheduler;

    /**
     * 创建任务池，运行线程等待处理Redis的消息
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler initTaskSecheduler() {
        if(taskScheduler != null) {
            return taskScheduler;
        }
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }

    @Bean
    public RedisMessageListenerContainer initRedisContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        // 设置连接工厂
        container.setConnectionFactory(this.redisConnectionFactory);
        // 设置运行任务池
        container.setTaskExecutor(this.taskScheduler);

        // 定义监控频道，名称为topic1
        ChannelTopic topic = new ChannelTopic("topic1");

        // 使用监听器监听Redis的消息
        container.addMessageListener(messageListener, topic);

        return container;
    }

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
