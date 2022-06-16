package com.lq.demo7;

import com.lq.util.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import sun.util.resources.cldr.ga.CurrencyNames_ga;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qili
 * @create 2022-06-16-10:10
 *
 * scenario 1: 消息过期
 */
public class Consumer01 {
    public static final String NORMAL_EXCHANGE = "normal_exchange";
    public static final String DEAD_EXCHANGE = "dead_exchange";
    public static final String NORMAL_QUEUE = "normal_queue";
    public static final String DEAD_QUEUE = "dead_queue";


    public static void main(String[] args) throws Exception {
        // 1. 获取信道
        Channel channel = RabbitMQUtils.getChannel();

        // 2. 声明交换机
        // 2.1 声明普通交换机
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);

        // 2.2 声明死信交换机
        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);

        // 3. 声明队列并绑定交换机，设置routing key
        // 3.1 声明普通队列并绑定交换机
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", "lisi");
        //arguments.put("x-max-length", 6);
        channel.queueDeclare(NORMAL_QUEUE, false, false, false, arguments);
        channel.queueBind(NORMAL_QUEUE, NORMAL_EXCHANGE, "zhangsan");

        // 3.2 声明死信队列并绑定死信交换机
        channel.queueDeclare(DEAD_QUEUE, false, false, false, null);
        channel.queueBind(DEAD_QUEUE, DEAD_EXCHANGE,"lisi");

        // 5. 接收消息-自动应答
        System.out.println("consumer01等待接收消息...");
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            String msg = new String(message.getBody(), "UTF-8");
            if("info4".equals(msg)) {
                // 拒绝
                System.out.println("consumer01拒绝接收消息：" + msg);
                channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
            } else {
                // 接收
                System.out.println("consumer01接收到消息:" + msg);
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            }
        };
        channel.basicConsume(NORMAL_QUEUE, false, deliverCallback, consumerTag -> {});
    }
}
