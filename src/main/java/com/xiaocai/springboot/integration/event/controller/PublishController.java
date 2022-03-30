package com.xiaocai.springboot.integration.event.controller;

import com.xiaocai.springboot.integration.event.service.impl.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/30 10:33
 */
@RestController
@RequestMapping("/event")
public class PublishController {

    @Autowired
    private PublisherService publisherService;

    @RequestMapping("/publish")
    @Transactional(propagation = Propagation.REQUIRED)
    public void publish(){

        publisherService.publishXiaocaiEvent();
        System.out.println("事件发布完成");
    }
}
