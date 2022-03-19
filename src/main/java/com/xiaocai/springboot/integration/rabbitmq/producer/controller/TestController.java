package com.xiaocai.springboot.integration.rabbitmq.producer.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public interface TestController {

    void test();

    @RequestMapping("/test1")
    void test_sender();

    @RequestMapping("/test_user")
    void test_user();

    @RequestMapping("/test_userQuery")
    void test_userQuery();

    @RequestMapping("/test_order")
    void test_order();

    @RequestMapping("/orderQuery")
    void orderQuery();

    @RequestMapping("/orderDetailQuery")
    void orderDetailQuery();

    void test_creditBank_type();

    void test_creditBank_all();

    void test_creditFinance_type();

    void test_creditFinance_all();

    @RequestMapping("/test_generateReports")
    void test_generateReports();
}
