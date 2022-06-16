package com.lq.rabbitmq.rabbitmqdemo02.consumer;

import com.lq.rabbitmq.rabbitmqdemo02.config.PublishConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author qili
 * @create 2022-06-16-22:08
 */
@Slf4j
@Component
public class WarningConsumer {
    @RabbitListener(queues = PublishConfirmConfig.WARNING_QUEUE_NAME)
    public void receiveMsg(Message message) {
        log.error("出现无法路由的消息：{}", new String(message.getBody()));
    }
}
