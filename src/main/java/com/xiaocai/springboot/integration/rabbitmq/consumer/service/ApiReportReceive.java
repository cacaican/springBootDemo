package com.xiaocai.springboot.integration.rabbitmq.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/18 16:06
 */
@Component
public class ApiReportReceive {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiReportReceive.class);

    @RabbitHandler
    @RabbitListener(queues = "api.report.payment")
    public void payment(String msg) {
        LOGGER.info("api.report.payment receive message: "+msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "api.report.refund")
    public void refund(String msg) {
        LOGGER.info("api.report.refund receive message: "+msg);
    }
}
