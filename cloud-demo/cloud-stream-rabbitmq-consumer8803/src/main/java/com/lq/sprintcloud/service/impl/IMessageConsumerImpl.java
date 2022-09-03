package com.lq.sprintcloud.service.impl;

import com.lq.sprintcloud.service.IMessageConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * @author qili
 * @create 2022-09-03-19:14
 */
@Slf4j
@Service
@EnableBinding(Sink.class)
public class IMessageConsumerImpl implements IMessageConsumer {
    @Value("${server.port}")
    private String serverPort;

    @Override
    public String receive() {
        return null;
    }

    @StreamListener(Sink.INPUT)
    public void getMsg(Message<String> message) {
        log.info("consumer8803---->接收到消息：" + message.getPayload() + "\tport:" + serverPort);
    }
}
