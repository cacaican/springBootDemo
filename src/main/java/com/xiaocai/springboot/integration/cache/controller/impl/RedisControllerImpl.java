package com.xiaocai.springboot.integration.cache.controller.impl;

import com.xiaocai.springboot.integration.cache.component.RedisCacheComponent;
import com.xiaocai.springboot.integration.cache.controller.RedisController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/15 17:05
 */
@Controller
@ResponseBody
@RequestMapping("/redis")
public class RedisControllerImpl implements RedisController {

    @Autowired
    private RedisCacheComponent redisCacheComponent;

    @Override
    @RequestMapping("/removeCache")
    public void removeCache(String cacheName, HttpServletResponse var2) {
        redisCacheComponent.removeCache(cacheName);
    }

    @Override
    @RequestMapping("/getCache")
    public List getCache(String cacheName, HttpServletResponse var2) {
        return  redisCacheComponent.getCache(cacheName);
    }

    @Override
    @RequestMapping("/addCache")
    public void addCache(String cacheName, String value) {
        redisCacheComponent.addCache(cacheName, value);
    }

    @Override
    @RequestMapping("/scanCache")
    public Map<String,Object> scanCache(String cacheName) {
        return redisCacheComponent.scanCache(cacheName);
    }
}
