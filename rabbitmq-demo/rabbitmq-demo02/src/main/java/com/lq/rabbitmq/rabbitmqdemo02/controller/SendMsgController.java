package com.lq.rabbitmq.rabbitmqdemo02.controller;

import com.lq.rabbitmq.rabbitmqdemo02.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author qili
 * @create 2022-06-16-13:42
 */
@Slf4j
@RestController
public class SendMsgController {
    public static final String EXCHANGE_X = "X";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/msg/{message}")
    public String sendMsg(@PathVariable("message") String msg) {
        log.info("当前时间：{}, 发送一条消息给两个TTL队列：{}", new Date().toString(), msg);
        rabbitTemplate.convertAndSend(EXCHANGE_X, "XA", "发送到QUEUEA(TTL=10s)的消息：" + msg);
        rabbitTemplate.convertAndSend(EXCHANGE_X, "XB", "发送到QUEUEB(TTL=40s)的消息：" + msg);
        return "消息发送成功！";
    }

    @GetMapping("/msg/{message}/{ttl}")
    public String sendMsg(
            @PathVariable("message") String msg,
            @PathVariable("ttl") String ttl
    ) {
        log.info("当前时间：{}, 发送消息给两个TTL队列：{}", new Date().toString(), msg);
        rabbitTemplate.convertAndSend(EXCHANGE_X, "XC", "发送到QUEUEC的消息：" + msg + ", ttl=" + ttl,
                correlationData -> {
                    correlationData.getMessageProperties().setExpiration(ttl);
                    return correlationData;
                });
        return "消息发送成功！";
    }

    @GetMapping("/delayed-msg/{message}/{delayedTime}")
    public String sendMsg(
            @PathVariable("message") String msg,
            @PathVariable("delayedTime") Integer delayedTime
    ) {
        log.info("当前时间：{}, 发送消息给delayedQueue队列：{}", new Date().toString(), msg);
        rabbitTemplate.convertAndSend(DelayedQueueConfig.EXCHANGE_NAME, DelayedQueueConfig.ROUTING_KEY, msg,
                correlationData -> {
                    correlationData.getMessageProperties().setDelay(delayedTime); // ms
                    return correlationData;
                });

        return "消息发送成功！";

    }
}
