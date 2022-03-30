package com.xiaocai.springboot.integration.event.service.impl;

import com.xiaocai.springboot.integration.event.evnet.XiaocaiEvent;
import com.xiaocai.springboot.integration.event.service.AbstractEventHandler;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/30 9:34
 */
@Component("xiaocaiHandler")
public class XiaocaiHandler implements AbstractEventHandler {


    @Override
    public void execute(ApplicationEvent event) {

        XiaocaiEvent xiaocaiEvent =(XiaocaiEvent) event;
        Map<String,Object> source = (Map<String,Object>) xiaocaiEvent.getSource();
        Set keySet = source.keySet();
        Iterator iterator = keySet.iterator();
        while(iterator.hasNext()) {
            String key = (String) iterator.next();
            Object o = source.get(key);
            System.out.println(xiaocaiEvent.getClass().getName()+"-------"+key +"---------------"+o);
        }
    }
}