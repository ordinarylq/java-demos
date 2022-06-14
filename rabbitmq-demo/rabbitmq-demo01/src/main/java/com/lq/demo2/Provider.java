package com.lq.demo2;

import com.lq.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.util.Scanner;

/**
 * @author qili
 * @create 2022-06-12-14:04
 * 消息未应答时消费者掉线则消息重新入队
 */
public class Provider {
    public static final String QUEUE_NAME = "test02_queue";

    public static void main(String[] args) throws Exception{
        // 1. 获取信道
        Channel channel = RabbitMQUtils.getChannel();

        // 2. 声明队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        // 3. 从控制台发送消息
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String message = scanner.next();
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
            System.out.println("生产者发布消息：" + message);
        }
    }
}
