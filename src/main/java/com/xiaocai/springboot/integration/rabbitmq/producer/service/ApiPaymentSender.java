package com.xiaocai.springboot.integration.rabbitmq.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/17 16:21
 */
@Component
public class ApiPaymentSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiPaymentSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void order(String msg){

       //((RabbitTemplate)rabbitTemplate).setConfirmCallback(); 设置发布确认后回调
       // ((RabbitTemplate)rabbitTemplate).setReturnsCallback(); 设置发布失败后回调
        LOGGER.info("api.payment.order send message: "+msg);
        ((RabbitTemplate)rabbitTemplate).convertAndSend("paymentExchange", "api.payment.order", msg);
    }

    public void orderQuery(String msg){
        LOGGER.info("api.payment.order.query send message: "+msg);
        rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order.query", msg);
    }

    public void orderDetailQuery(String msg){
        LOGGER.info("api.payment.order.detail.query send message: "+msg);
        rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order.detail.query", msg);
    }
}
