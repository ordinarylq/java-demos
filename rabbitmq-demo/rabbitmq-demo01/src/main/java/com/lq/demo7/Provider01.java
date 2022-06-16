package com.lq.demo7;

import com.lq.util.RabbitMQUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

/**
 * @author qili
 * @create 2022-06-16-10:25
 * scenario 1: 消息过期
 */
public class Provider01 {
    public static final String NORMAL_EXCHANGE = "normal_exchange";

    public static void main(String[] args) throws Exception {
        // 1. 获取信道
        Channel channel = RabbitMQUtils.getChannel();

        // 2. 声明交换机
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);

        // 3. 发送消息
        // 设置消息的过期时间属性
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("10000").build();
        for (int i = 0; i < 10; i++) {
            String message = "info" + i;
            channel.basicPublish(NORMAL_EXCHANGE, "zhangsan", properties,
                    message.getBytes("UTF-8"));
            System.out.println("provider 发出消息：" + message);
        }
    }
}
