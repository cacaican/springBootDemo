package com.xiaocai.springboot.integration.rabbitmq.producer.controller.impl;

import com.xiaocai.springboot.integration.rabbitmq.producer.controller.TestController;
import com.xiaocai.springboot.integration.rabbitmq.consumer.service.TestConsumer;
import com.xiaocai.springboot.integration.rabbitmq.producer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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

    /*--------------------------以上的是简单的发送和接受*/
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

    /*-------------------以上两个用的topic中的*匹配，user可以发送和接受，而userQuery绑定的是api.core.user.query 交换机绑定的是api.core.*只能匹配一行-------------------------*/


    @Autowired
    private ApiPaymentSender apiPaymentSender;

    @Override
    @RequestMapping("/test_order")
    public void test_order() {
        apiPaymentSender.order("订单管理！");
    }

    @Override
    @RequestMapping("/orderQuery")
    public void orderQuery() {
        apiPaymentSender.orderQuery("查询订单信息！");
    }

    @Override
    @RequestMapping("/orderDetailQuery")
    public void orderDetailQuery() {
        apiPaymentSender.orderDetailQuery("查询订单详情信息！");
    }
    /*-------------------以上三个用的topic中的#匹配，所以都能接受和发送-------------------------*/

    @Autowired
    private ApiCreditSender apiCreditSender;

    @Override
    @RequestMapping("/test_creditBank_type")
    public void test_creditBank_type() {
        Map<String,Object> head = new HashMap<>();
        head.put("type", "cash");
        apiCreditSender.creditBank(head, "银行授信(部分匹配)");
    }

    @Override
    @RequestMapping("/test_creditBank_all")
    public void test_creditBank_all() {
        Map<String,Object> head = new HashMap<>();
        head.put("type", "cash");
        head.put("aging", "fast");
        apiCreditSender.creditBank(head, "银行授信(全部匹配)");
    }

    @Override
    @RequestMapping("/test_creditFinance_type")
    public void test_creditFinance_type() {
        Map<String,Object> head = new HashMap<>();
        head.put("type", "cash");
        apiCreditSender.creditFinance(head, "金融公司授信(部分匹配)");
    }

    @Override
    @RequestMapping("/test_creditFinance_all")
    public void test_creditFinance_all() {
        Map<String,Object> head = new HashMap<>();
        head.put("type", "cash");
        head.put("aging", "fast");
        apiCreditSender.creditFinance(head, "金融公司授信(全部匹配)");
    }
    /*--------------------------------------*/

    @Autowired
    private ApiReportSender apiReportSender;

    @Override
    @RequestMapping("/test_generateReports")
    public void test_generateReports() {
        apiReportSender.generateReports("开始生成报表！");
    }
    /*-上面这个测试类中，发送者发送方法中convertAndSend（交换机名称，队列名称，消息实体）
    而该交换机是FanoutExchange,绑定了两个队列，所以两个队列都能接收到
    -------------------------*/


    /*此外，rabbitmq中有setCallback机制与setReturncallBack机制，可以确认消息是否发送成功*/


}
