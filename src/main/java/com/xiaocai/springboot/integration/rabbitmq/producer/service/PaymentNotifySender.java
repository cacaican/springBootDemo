package com.xiaocai.springboot.integration.rabbitmq.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/17 16:09
 */
@Component
public class PaymentNotifySender {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentNotifySender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sender(String msg){
        rabbitTemplate.convertAndSend("notify.payment", msg);
    }
}