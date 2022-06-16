package com.lq.demo6;

import com.lq.util.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

/**
 * @author qili
 * @create 2022-06-16-8:41
 */
public class ReceiveLogsLogic {
    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception {
        // 1. 获取信道
        Channel channel = RabbitMQUtils.getChannel();

        // 2. 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        // 3. 声明临时队列
        String queueName = channel.queueDeclare().getQueue();

        // 4. 绑定队列
        //channel.queueBind(queueName, EXCHANGE_NAME, "*.cron");
        channel.queueBind(queueName, EXCHANGE_NAME, "info.order");
        channel.queueBind(queueName, EXCHANGE_NAME, "error.*");

        // 5. 接收消息-自动应答
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            String m = new String(message.getBody(), "UTF-8");
            System.out.println("接收到消息：[" + m + "] " +
                    "with Routing key: [" + message.getEnvelope().getRoutingKey() + "]");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});

    }
}
