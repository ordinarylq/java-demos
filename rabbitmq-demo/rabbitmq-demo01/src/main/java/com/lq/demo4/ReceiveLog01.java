package com.lq.demo4;

import com.lq.util.RabbitMQUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

/**
 * @ClassName: ReceiveLog01
 * @Author: LiQi
 * @Date: 2022-06-15 14:10
 * @Version: V1.0
 * @Description:
 */
public class ReceiveLog01 {
    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception{
        // 1. 获取信道
        Channel channel = RabbitMQUtils.getChannel();

        // 2. 声明交换机(fanout类型)
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 3. 声明队列(使用临时队列)
        String queueName = channel.queueDeclare().getQueue();

        // 4. 交换机绑定队列
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        // 5. 获取消息(使用手动应答)
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("接收到消息：" + new String(message.getBody()));
            // 不进行批量应答，避免消息丢失
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        };

        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag + "消费者取消消费...");
        };

        channel.basicConsume(queueName, false, deliverCallback, cancelCallback);

    }
}
