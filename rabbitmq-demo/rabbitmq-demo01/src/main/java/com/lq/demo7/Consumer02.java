package com.lq.demo7;

import com.lq.util.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author qili
 * @create 2022-06-16-10:32
 */
public class Consumer02 {
    public static final String DEAD_EXCHANGE = "dead_exchange";
    public static final String DEAD_QUEUE = "dead_queue";

    public static void main(String[] args) throws Exception {
        // 1. 获取信道
        Channel channel = RabbitMQUtils.getChannel();

        // 2. 声明交换机
        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);

        // 3. 声明队列
        channel.queueDeclare(DEAD_QUEUE, false, false, false, null);

        // 4. 交换机绑定队列
        channel.queueBind(DEAD_QUEUE, DEAD_EXCHANGE, "lisi");

        // 5. 接收消息
        System.out.println("consumer02等待接收消息...");
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            String msg = new String(message.getBody(), "UTF-8");
            System.out.println("consumer02接收到消息:" + msg);
        };
        channel.basicConsume(DEAD_QUEUE, true, deliverCallback, consumerTag -> {});
    }
}
