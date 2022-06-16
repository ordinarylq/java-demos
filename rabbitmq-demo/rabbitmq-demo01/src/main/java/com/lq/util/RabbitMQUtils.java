package com.lq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author qili
 * @create 2022-06-12-13:29
 */
public class RabbitMQUtils {
    /**
     * 获取rabbitmq的一个信道
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Channel getChannel() throws IOException, TimeoutException {
        // 1. 新建工厂对象
        ConnectionFactory factory = new ConnectionFactory();

        // 2. 设置参数
        factory.setHost("192.168.145.128");
//        factory.setHost("192.168.42.129");
        factory.setUsername("admin");
        factory.setPassword("123456");
        //factory.setHandshakeTimeout(10000);

        // 3. 建立连接、信道并返回
        Connection connection = factory.newConnection();
        return connection.createChannel();
    }
}
