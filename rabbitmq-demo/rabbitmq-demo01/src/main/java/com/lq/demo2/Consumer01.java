package com.lq.demo2;

import com.lq.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Delivery;

/**
 * @author qili
 * @create 2022-06-12-14:07
 */
public class Consumer01 {
    public static final String QUEUE_NAME = "test02_queue";

    public static void main(String[] args) throws Exception{
        // 1. 获取信道
        Channel channel = RabbitMQUtils.getChannel();
        // 设置prefetch为1
        int prefetch = 2;
        channel.basicQos(prefetch);

        // 2. 接收消息
        System.out.println("Consumer01准备接受消息...");
        channel.basicConsume(
                QUEUE_NAME,
                false,
                (consumerTag, message) -> {
                    try {
                        Thread.sleep(1000);
                        System.out.println("接收到消息：" + new String(message.getBody()));
                        channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                consumerTag -> System.out.println(consumerTag + "消费者消费取消...")
        );
    }
}
