package com.lq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Delivery;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author qili
 * @create 2022-06-12-10:49
 */
public class DemoTest01 {
    public static final String QUEUE_NAME = "hello";

    private ConnectionFactory getConnectionFactory() {
        // 1. 新建工厂对象
        ConnectionFactory factory = new ConnectionFactory();

        // 2. 设置参数
        factory.setHost("192.168.145.128");
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setHandshakeTimeout(10000);

        // 3. 返回工厂对象
        return factory;
    }

    @Test
    void testProvider() throws IOException, TimeoutException {
        // 1. 获取连接工厂，并获取连接
        ConnectionFactory connectionFactory = getConnectionFactory();
        Connection connection = connectionFactory.newConnection();

        // 2. 创建信道
        Channel channel = connection.createChannel();

        // 3. 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 4. 发送消息
        String message = "Hello, RabbitMQ!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("消息发送成功！");

    }

    @Test
    void testConsumer() throws IOException, TimeoutException {
        // 1. 获取连接
        ConnectionFactory connectionFactory = getConnectionFactory();
        Connection connection = connectionFactory.newConnection();

        // 2. 创建信道
        Channel channel = connection.createChannel();
        System.out.println("等待接收消息...");

        // 3. 接收消息，设置回调
        channel.basicConsume(
                QUEUE_NAME,true,
                (consumerTag, message) -> System.out.println(new String(message.getBody())),
                consumerTag -> System.out.println("消费消息中断...")
        );
    }
}
