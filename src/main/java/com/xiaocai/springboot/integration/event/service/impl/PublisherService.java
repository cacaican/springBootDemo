package com.xiaocai.springboot.integration.event.service.impl;

import com.xiaocai.springboot.integration.event.evnet.XiaocaiEvent;
import com.xiaocai.springboot.integration.event.service.AbstractEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @description: 发布事件服务类
 * 两种方法：
 * 方法一，实例化ApplicationEventPublisher对象，通过ApplicationEventPublisher。publish（event）来发布事件
 * 方法二，获取上下文，通过applicationContext.publishEvent(事件)来发布事件
 * @author: xiaocai
 * @time: 2022/3/30 9:33
 */
@Service
public class PublisherService  {

    private Long requestMills;
    private Long publishMills;


    /**
     * eventPublisher
     */
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    /**
     * coinsHandle
     */
    @Autowired
    @Qualifier("xiaocaiHandler")
    private AbstractEventHandler xiaocaiHandler;

    public void publishXiaocaiEvent(){
        HashMap<String, Object> map = new HashMap<>();

        map.put("1","xiaocai");
        map.put("2","是");
        map.put("3","天");
        map.put("4","才");
        map.put("5","a");
        eventPublisher.publishEvent(new XiaocaiEvent(xiaocaiHandler,map));

    }
}
