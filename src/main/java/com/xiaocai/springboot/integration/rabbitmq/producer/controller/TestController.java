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
}
