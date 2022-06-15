package com.lq.demo5;

import com.lq.util.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * @ClassName: ReceiveLogDirect01
 * @Author: LiQi
 * @Date: 2022-06-15 15:11
 * @Version: V1.0
 * @Description:
 */
public class ReceiveLogDirect02 {
    public static final String EXCHANGE_NAME = "direct_logs";
    public static final String ROUTING_KEY3 = "error";
    public static final String QUEUE_NAME = "disk";

    public static void main(String[] args) throws Exception{
        // 1. 获取信道
        Channel channel = RabbitMQUtils.getChannel();

        // 2. 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        // 3. 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 4. 交换机绑定队列
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY3);

        // 5. 接收消息-手动应答
        System.out.println("消费者接收消息");
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("消费者接收到消息：" + new String(message.getBody()));
            // 手动应答
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        };

        CancelCallback cancelCallback = consumerTag -> System.out.println(consumerTag + "消费者取消消费...");

        channel.basicConsume(QUEUE_NAME, false, deliverCallback, cancelCallback);
    }
}
