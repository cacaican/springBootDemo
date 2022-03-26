package com.xiaocai.springboot.integration.ssm.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @description: 自定义容器
 * @author: xiaocai
 * @time: 2022/3/24 17:01
 */
@Component
public class BaseServiceLocator  implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    protected static Logger logger = LoggerFactory.getLogger(BaseServiceLocator.class);
    private static Map<String, ApplicationContext> applicationContexts = new HashMap();
    private static Boolean isCompleted = false;

    public static Object getService(String serviceName) {
        try {
            Iterator var1 = applicationContexts.values().iterator();

            while(var1.hasNext()) {
                ApplicationContext ac = (ApplicationContext)var1.next();
                if (ac != null) {
                    if (ac.containsBean(serviceName)) {
                        return ac.getBean(serviceName);
                    }
                } else if (logger.isErrorEnabled()) {
                    logger.error("上下文ApplicationContext is null");
                }
            }

            return null;
        } catch (BeansException var3) {
            var3.printStackTrace();
            if (logger.isErrorEnabled()) {
                logger.error("上下文getApplicationContext throw exception", var3);
            }
            return null;
        }
    }

    /** ApplicationContextAware接口必须实现的方法，为当前任务设置上下文
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String id = null;
        if (applicationContext != null) {
            id = applicationContext.getDisplayName();
            if (StringUtils.isBlank(id)) {
                id = applicationContext.getId();
            }
        }

        if (StringUtils.isNotBlank(id)) {
            applicationContexts.put(id, applicationContext);
            if (logger.isInfoEnabled()) {
                logger.info("上下文:" + id + " 成功保存!");
            }
        } else if (logger.isErrorEnabled()) {
            logger.error("上下文缺失id");
        }
    }

    /** ApplicationListener接口必须实现的方法，创建事件触发时候需要的操作，这里没有做什么，就控制台展示了下
     * @param event 事件
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        ApplicationContext ac = event.getApplicationContext();
        String displayName = ac.getDisplayName();
        logger.info("展示名称:" + displayName + " is completed!");
        ApplicationContext parent = ac.getParent();
        if (parent != null) {
            String parentName = parent.getDisplayName();
            logger.info("Parent ApplicationContext:" + parentName + " is completetd!");
            System.out.println("Application Platform has successfully started!");
            System.out.println("You can enjoy it!");
            isCompleted = true;
        }
    }


}
