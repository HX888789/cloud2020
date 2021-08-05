package com.hx.cloud.service.impl;

import com.hx.cloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@EnableBinding(Source.class) //定义消息的推送管道
public class IMessageProviderImpl implements IMessageProvider {
    @Autowired
    //消息注入管道
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("************serial+{}",serial);
        return null;
    }
}
