package com.lq.demo5;

import com.lq.util.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName: EmitLogDirect
 * @Author: LiQi
 * @Date: 2022-06-15 15:18
 * @Version: V1.0
 * @Description:
 */
public class EmitLogDirect {
    public static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception {
        // 1. 获取信道
        Channel channel = RabbitMQUtils.getChannel();

        // 2. 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        // 3. 发布消息
        HashMap<String, String> bindingKeyMap = new HashMap<>();
        bindingKeyMap.put("info", "info级别的消息");
        bindingKeyMap.put("warning", "warning级别的消息");
        bindingKeyMap.put("error", "error级别的消息");
        bindingKeyMap.put("debug", "debug级别的消息");
        Set<String> bindingKeys = bindingKeyMap.keySet();
        Iterator<String> iterator = bindingKeys.iterator();
        while(iterator.hasNext()) {
            String bindingKey = iterator.next();
            String message = bindingKeyMap.get(bindingKey);
            // 发布消息
            channel.basicPublish(EXCHANGE_NAME, bindingKey, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("生产者发出消息：" + message);
        }
    }
}
