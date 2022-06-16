package com.lq.rabbitmq.rabbitmqdemo02.consumer;

import com.lq.rabbitmq.rabbitmqdemo02.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author qili
 * @create 2022-06-16-16:33
 */
@Slf4j
@Component
public class DelayedQueueConsumer {

    @RabbitListener(queues = DelayedQueueConfig.QUEUE_NAME)
    public void receiveMsg(Message message) throws UnsupportedEncodingException {
        String msg = new String(message.getBody(), "UTF-8");
        log.info("当前时间：{}, 收到delayedQueue消息：{}", new Date().toString(), msg);
    }
}
