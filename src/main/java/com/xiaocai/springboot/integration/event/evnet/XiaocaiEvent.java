package com.xiaocai.springboot.integration.event.evnet;

import com.xiaocai.springboot.integration.event.service.AbstractEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Clock;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/30 9:41
 */
public class XiaocaiEvent extends AbstractEvent {


    /**
     * 设置当前event对应的处理handler
     * */
/*
    @Qualifier(value="xiaocaiHandler") 这里的handler由发布的时候传入
*/
    private AbstractEventHandler eventHandler;

    /**
     * @param eventHandler policyHandle
     * @param source       source
     */
    public XiaocaiEvent(AbstractEventHandler eventHandler, Object source) {
        super(source);
        this.eventHandler = eventHandler;
    }

    public AbstractEventHandler getHandle() {
        return eventHandler;
    }
}
