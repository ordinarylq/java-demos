package com.lq.springcloud.service.impl;

import com.lq.springcloud.service.IMessageConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * @author qili
 * @create 2022-08-30-22:24
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
        log.info("consumer8802---->接收到消息：" + message.getPayload() + "\tport:" + serverPort);
    }
}
