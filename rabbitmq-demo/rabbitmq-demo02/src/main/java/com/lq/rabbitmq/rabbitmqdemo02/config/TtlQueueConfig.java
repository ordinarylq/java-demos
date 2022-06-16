package com.lq.rabbitmq.rabbitmqdemo02.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qili
 * @create 2022-06-16-13:27
 */
@Configuration
public class TtlQueueConfig {

    public static final String EXCHANGE_X = "X";
    public static final String EXCHANGE_Y = "Y";

    public static final String QUEUE_A = "QA";
    public static final String QUEUE_B = "QB";
    public static final String QUEUE_D = "QD";

    public static final Integer TTL01 = 10000;
    public static final Integer TTL02 = 40000;

    @Bean
    public DirectExchange exchangeX() {
        return new DirectExchange(EXCHANGE_X);
    }

    @Bean
    public DirectExchange exchangeY() {
        return new DirectExchange(EXCHANGE_Y);
    }

    @Bean
    public Queue queueA() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", EXCHANGE_Y);
        arguments.put("x-dead-letter-routing-key", "YD");
        arguments.put("x-message-ttl", TTL01);
        return new Queue(QUEUE_A, false, false, false, arguments);
    }

    //binding
    @Bean
    public Binding queueAToExchangeX() {
        DirectExchange exchangeX = exchangeX();
        Queue queueA = queueA();
        return BindingBuilder.bind(queueA).to(exchangeX).with("XA");
    }

    // 另一种方式创建队列
    @Bean
    public Queue queueB() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", EXCHANGE_Y);
        arguments.put("x-dead-letter-routing-key", "YD");
        arguments.put("x-message-ttl", TTL02);
        return QueueBuilder.durable(QUEUE_B).withArguments(arguments).build();
    }

    // binding
    @Bean
    public Binding queueBToExchangeX() {
        DirectExchange exchangeX = exchangeX();
        Queue queueB = queueB();
        return BindingBuilder.bind(queueB).to(exchangeX).with("XB");
    }

    @Bean
    public Queue queueD() {
        HashMap<String, Object> arguments = new HashMap<>();
        return QueueBuilder.durable(QUEUE_D).build();
    }

    @Bean
    public Binding queueDToExchangeY() {
        DirectExchange exchangeY = exchangeY();
        Queue queueD = queueD();
        return BindingBuilder.bind(queueD).to(exchangeY).with("YD");
    }

    public static final String QUEUE_C = "QC";
    @Bean
    public Queue queueC() {
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", EXCHANGE_Y);
        arguments.put("x-dead-letter-routing-key", "YD");
        return QueueBuilder.durable(QUEUE_C).withArguments(arguments).build();
    }

    @Bean
    public Binding queueCToExchangeX() {
        DirectExchange exchangeX = exchangeX();
        Queue queueC = queueC();
        return BindingBuilder.bind(queueC).to(exchangeX).with("XC");
    }


}
