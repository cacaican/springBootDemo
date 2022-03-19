package com.xiaocai.springboot.integration.rabbitmq.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/18 14:48
 */
@Component
public class ApiCreditSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiCreditSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void creditBank(Map<String, Object> head, String msg){
        LOGGER.info("credit.bank send message: "+msg);
        rabbitTemplate.convertAndSend("creditBankExchange", "credit.bank", getMessage(head, msg));
    }

    public void creditFinance(Map<String, Object> head, String msg){
        LOGGER.info("credit.finance send message: "+msg);
        rabbitTemplate.convertAndSend("creditFinanceExchange", "credit.finance", getMessage(head, msg));
    }

    private Object getMessage(Map<String, Object> head, String msg) {
        //todo
        return new Object();
    }

}
