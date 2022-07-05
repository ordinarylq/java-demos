package com.lq.redisdemo2.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author qili
 * @create 2022-07-05-19:03
 */
@Component
public class RedisMessageListener implements MessageListener {
    // 得到消息后的处理方法
    // message表示发送过来的消息
    // pattern表示频道名称
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 消息体
        String body = new String(message.getBody());
        // 渠道名称
        String topic = new String(pattern);
        System.out.println("MessageListener.onMessage() threadName=" + Thread.currentThread().getName());
        System.out.println(body);
        System.out.println(topic);
    }
}
