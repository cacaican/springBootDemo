package com.xiaocai.springboot.integration.rabbitmq.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/17 16:19
 */
@Component
public class ApiPaymentReceive {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiCoreReceive.class);

    @RabbitHandler
    @RabbitListener(queues = "api.payment")
    public void order(String msg) {
        LOGGER.info("api.payment.order receive message: "+msg);
    }
}
