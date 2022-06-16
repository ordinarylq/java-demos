package com.lq.rabbitmq.rabbitmqdemo02.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author qili
 * @create 2022-06-16-13:49
 */
@Slf4j
@Component
public class DeadLetterQueueConsumer {

    @RabbitListener(queues = "QD")
    public void receiveMsg(Message message, Channel channel) throws UnsupportedEncodingException {
        String msg = new String(message.getBody(), "UTF-8");
        log.info("当前时间：{}, 收到延时消息：{}", new Date().toString(), msg);
    }
}
