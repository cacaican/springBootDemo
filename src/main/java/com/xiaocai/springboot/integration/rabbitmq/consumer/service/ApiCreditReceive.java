package com.xiaocai.springboot.integration.rabbitmq.consumer.service;


import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*https://www.cnblogs.com/skychenjiajun/p/9037324.html*/
/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/18 15:03
 */
@Component
public class ApiCreditReceive {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiCreditReceive.class);


    @RabbitHandler
    @RabbitListener(queues = "credit.bank")
    public void creditBank(String msg, Channel channel) {
        LOGGER.info("credit.bank receive message: "+msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "credit.finance")
    public void creditFinance(String msg, Channel channel) {
        LOGGER.info("credit.finance receive message: "+msg);
    }
}
