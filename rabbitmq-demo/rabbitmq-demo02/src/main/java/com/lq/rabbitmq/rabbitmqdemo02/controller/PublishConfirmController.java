package com.lq.rabbitmq.rabbitmqdemo02.controller;

import com.lq.rabbitmq.rabbitmqdemo02.config.PublishConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qili
 * @create 2022-06-16-17:45
 */
@RestController
@Slf4j
public class PublishConfirmController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/msg/confirm/{message}")
    public String sendMsg(@PathVariable("message") String msg) {
        CorrelationData correlationData = new CorrelationData("1");
        rabbitTemplate.convertAndSend(
                PublishConfirmConfig.EXCHANGE_NAME + "1",
                PublishConfirmConfig.ROUTING_KEY,
                msg, correlationData);
        log.info("发送消息内容：{}", msg);
        return "消息发送成功！";
    }
}
