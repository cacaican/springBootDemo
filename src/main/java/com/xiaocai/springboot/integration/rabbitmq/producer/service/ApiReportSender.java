package com.xiaocai.springboot.integration.rabbitmq.producer.service;

import com.xiaocai.springboot.integration.rabbitmq.consumer.service.ApiReportReceive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/18 16:22
 */
@Component
public class ApiReportSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiReportReceive.class);

    public void generateReports(String msg){
        LOGGER.info("api.generate.reports send message: "+msg);
        rabbitTemplate.convertAndSend("reportExchange", "api.generate.reports", msg);
    }
}