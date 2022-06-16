package com.lq.rabbitmq.rabbitmqdemo02.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author qili
 * @create 2022-06-16-17:34
 */
@Slf4j
@Configuration
public class PublishConfirmConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback{
    public static final String EXCHANGE_NAME = "confirm.exchange";
    public static final String QUEUE_NAME = "confirm.queue";
    public static final String ROUTING_KEY = "key1";

    public static final String BACKUP_EXCHANGE_NAME = "backup.exchange";
    public static final String BACKUP_QUEUE_NAME = "backup.queue";
    public static final String WARNING_QUEUE_NAME = "warning.queue";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 由于ConfirmCallback是RabbitTemplate的一个内部的函数式接口，
    // 因此需要手动设置
    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    @Bean
    public DirectExchange confirmExchange() {
//        return new DirectExchange(EXCHANGE_NAME);
        return ExchangeBuilder.directExchange(EXCHANGE_NAME).alternate(BACKUP_EXCHANGE_NAME).durable(true).build();
    }

    @Bean
    public Queue confirmQueue() {
        return new Queue(QUEUE_NAME,true, false, false);
    }

    @Bean
    public Binding queueToExchange() {
        DirectExchange confirmExchange = confirmExchange();
        Queue confirmQueue = confirmQueue();
        return BindingBuilder.bind(confirmQueue).to(confirmExchange).with(ROUTING_KEY);
    }

    @Bean
    public FanoutExchange backupExchange() {
        return new FanoutExchange(BACKUP_EXCHANGE_NAME);
    }

    @Bean
    public Queue backupQueue() {
        return QueueBuilder.durable(BACKUP_QUEUE_NAME).build();
    }

    @Bean Binding backupQueueToBackupExchange() {
        FanoutExchange backupExchange = backupExchange();
        Queue backupQueue = backupQueue();
        return BindingBuilder.bind(backupQueue).to(backupExchange);
    }

    @Bean
    public Queue warningQueue() {
        return QueueBuilder.durable(WARNING_QUEUE_NAME).build();
    }

    @Bean
    public Binding warningQueueToBackupExchange() {
        FanoutExchange backupExchange = backupExchange();
        Queue warningQueue = warningQueue();
        return BindingBuilder.bind(warningQueue).to(backupExchange);
    }


    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData!=null?correlationData.getId():"";
        if(ack) {
            // 消息发布成功
            log.info("交换机已经收到id为[{}]的消息", id);
        } else {
            // 消息发布失败
            log.info("交换机未收到id为[{}]的消息", id);
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        String msg = new String(returned.getMessage().getBody());
        log.error("消息[{}]被退回，原因:{}, 交换机: {}, routing key: {}",
                msg, returned.getReplyText(), returned.getExchange(), returned.getRoutingKey());
    }
}
