package com.lq.demo6;

import com.lq.util.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * @author qili
 * @create 2022-06-16-8:31
 */
public class EmitLogTopic {
    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception{
        // 1. 获取信道
        Channel channel = RabbitMQUtils.getChannel();

        // 2. 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        // 3. 设置消息
        HashMap<String, String> map = new HashMap<>();
        map.put("info.cron", "[info]级别的消息 from [cron]");
        map.put("debug.rpc", "[debug]级别的消息 from [rpc]");
        map.put("error.order", "[error]级别的消息 from [order]");
        map.put("info.order", "[info]级别的消息 from [order]");
        map.put("error.cron", "[error]级别的消息 from [cron]");

        // 4. 发送消息
        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();
        while(iterator.hasNext()) {
            String routingKey = iterator.next();
            String message = map.get(routingKey);
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
            System.out.println("Provider publishes a message: [" + message + "] with routing key[" + routingKey + "]");
        }

    }
}
