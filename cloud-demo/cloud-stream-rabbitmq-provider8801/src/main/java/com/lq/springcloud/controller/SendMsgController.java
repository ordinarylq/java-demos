package com.lq.springcloud.controller;

import com.lq.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qili
 * @create 2022-08-30-21:31
 */
@RestController
public class SendMsgController {
    @Autowired
    private IMessageProvider messageProvider;

    @GetMapping("/msg")
    public String sendMessage() {
        return this.messageProvider.send();
    }
}
