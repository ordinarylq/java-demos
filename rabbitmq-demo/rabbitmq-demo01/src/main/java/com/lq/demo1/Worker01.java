package com.lq.demo1;

import com.lq.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Delivery;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author qili
 * @create 2022-06-12-13:29
 */
public class Worker01 {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1. 获取信道
        Channel channel = RabbitMQUtils.getChannel();

        // 2. 接收消息
        System.out.println("consumer02准备消费...");
        channel.basicConsume(
                QUEUE_NAME,
                true,
                (consumerTag, message) -> System.out.println(new String(message.getBody())),
                consumerTag -> System.out.println(consumerTag + "消费者取消消费..."));
    }
}
