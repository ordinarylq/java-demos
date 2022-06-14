package com.lq.demo1;

import com.lq.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author qili
 * @create 2022-06-12-13:36
 */
public class Task01 {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1. 获取信道
        Channel channel = RabbitMQUtils.getChannel();

        // 2. 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 3. 从控制台获取消息并发送
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String message = scanner.next();
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("消息发送完成：" + message);
        }
    }
}
