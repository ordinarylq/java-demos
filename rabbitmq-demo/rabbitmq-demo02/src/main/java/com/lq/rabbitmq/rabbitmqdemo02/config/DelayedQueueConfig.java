package com.lq.rabbitmq.rabbitmqdemo02.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author qili
 * @create 2022-06-16-16:26
 */
@Configuration
public class DelayedQueueConfig {

    public static final String EXCHANGE_NAME = "delayed.exchange";
    public static final String QUEUE_NAME = "delayed.queue";
    public static final String ROUTING_KEY = "delayed.routingkey";

    @Bean
    public CustomExchange delayedExchange() {
        HashMap<String, Object> arguments = new HashMap<>();
        // 指定延迟交换机的分发消息模式
        // 可以简单理解：
        // x-delayed-message 指定了交换机会延迟发送消息到队列
        // x-delayed-type 指定了交换机发送消息的规则，是广播、路由...
        arguments.put("x-delayed-type", "direct");
        return new CustomExchange(EXCHANGE_NAME, "x-delayed-message", true, false, arguments);
    }

    @Bean
    public Queue delayedQueue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    public Binding delayedQueueToDelayedExchange() {
        CustomExchange delayedExchange = delayedExchange();
        Queue delayedQueue = delayedQueue();
        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(ROUTING_KEY).noargs();
    }
}
