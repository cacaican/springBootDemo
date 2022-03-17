package com.xiaocai.springboot.integration.rabbitmq.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/17 16:08
 */
@Component
@RabbitListener(queues = "notify.payment")
public class PaymentNotifyReceive {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentNotifyReceive.class);

    @RabbitHandler
    public void receive(String msg) {
        LOGGER.info("notify.payment receive message: "+msg);
    }
}
