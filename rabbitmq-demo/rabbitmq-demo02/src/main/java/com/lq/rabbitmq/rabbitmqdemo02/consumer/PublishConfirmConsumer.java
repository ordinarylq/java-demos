package com.lq.rabbitmq.rabbitmqdemo02.consumer;

import com.lq.rabbitmq.rabbitmqdemo02.config.PublishConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @author qili
 * @create 2022-06-16-17:43
 */
@Slf4j
@Component
public class PublishConfirmConsumer {

    @RabbitListener(queues = PublishConfirmConfig.QUEUE_NAME)
    public void receiveMsg(Message message) throws UnsupportedEncodingException {
        String msg = new String(message.getBody(), "UTF-8");
        log.info("接收到队列" + PublishConfirmConfig.QUEUE_NAME + "的消息：" + msg);
    }
}
