package com.xiaocai.springboot.integration.rabbitmq.producer.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

/**
 * 获取IP
 * @author lijin.l.shi@pwc.com
 *
 */
public class MqBaseIPConsts {
    /**
     * 日志控制器
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(MqBaseIPConsts.class);
    /**
     * 本地IP
     */
    public static String IPADDRESS = "localhost";

    static {
        try {
            IPADDRESS = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            LOGGER.error("获取IP失败");
        }
    }
}
