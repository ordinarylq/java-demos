package com.lq.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.lq.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author qili
 * @create 2022-08-30-21:27
 */
@Slf4j
@EnableBinding(Source.class)
public class IMessageProviderImpl implements IMessageProvider {
    // 消息的发送信道
    @Autowired
    @Qualifier(value = "output")
    private MessageChannel outPutChannel;

    @Override
    public String send() {
        String message = IdUtil.simpleUUID();
        // 通过信道发送消息
        this.outPutChannel.send(MessageBuilder.withPayload(message).build());
        log.info("****message:" + message);
        return message;
    }
}
