package com.xiaocai.springboot.integration.rabbitmq.producer.controller.impl;

import com.xiaocai.springboot.integration.rabbitmq.producer.controller.TestController;
import com.xiaocai.springboot.integration.rabbitmq.consumer.service.TestConsumer;
import com.xiaocai.springboot.integration.rabbitmq.producer.service.ApiCoreSender;
import com.xiaocai.springboot.integration.rabbitmq.producer.service.PaymentNotifySender;
import com.xiaocai.springboot.integration.rabbitmq.producer.service.TestProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/17 15:34
 */
@Controller
@RequestMapping("/mq")
@ResponseBody
public class TestControllerImpl implements TestController {


    @Autowired
    private TestProducer testProducer;
    @Autowired
    private PaymentNotifySender sender;

    @Override
    @RequestMapping("/test")
    public void test() {
        testProducer.testSendMessage();
    }


    /*使用方式：
    * 1.先注入一个quene：notify.payment
    * 2.定义一个监听：PaymentNotifyReceive，类上使用注解@RabbitListener(queues = "notify.payment")
    *       表示监听指定队列
    * 3.监听类中定义receive（msg）方法，方法上使用注解 @RabbitHandler
    * 4.定义发送/生产者: 生产者中自动注入qmqpTemplate中，通过rabbitTemplate.convertAndSend(" quene名称", 发送消息)
    * 5.触发生产者即可
     * */
    @Override
    @RequestMapping("/test1")
    public void test_sender() {
        sender.sender("支付订单号："+System.currentTimeMillis());
    }

    @Autowired
    private ApiCoreSender apiCoreSender;

    @Override
    @RequestMapping("/test_user")
    public void test_user() {
        apiCoreSender.user("用户管理！");
    }

    @Override
    @RequestMapping("/test_userQuery")
    public void test_userQuery() {
        apiCoreSender.userQuery("查询用户信息！");
    }

}
