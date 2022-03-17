package com.xiaocai.springboot.integration.rabbitmq.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/17 16:20
 */
@Component
public class ApiCoreSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiCoreSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void user(String msg){
        LOGGER.info("api.core.user send message: "+msg);
        rabbitTemplate.convertAndSend("coreExchange", "api.core.user", msg);
    }

    public void userQuery(String msg){
        LOGGER.info("api.core.user.query send message: "+msg);
        rabbitTemplate.convertAndSend("coreExchange", "api.core.user.query", msg);
    }
}
