package com.xiaocai.springboot.integration.redis.controller.impl;

import com.xiaocai.springboot.integration.redis.component.CacheComponent;
import com.xiaocai.springboot.integration.redis.controller.RedisController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/15 17:05
 */
@Controller
public class RedisControllerImpl implements RedisController {

    @Autowired
    private CacheComponent cacheComponent;
    @Override
    public void removeCache(String cacheName, HttpServletResponse var2) {
        cacheComponent.removeCache(cacheName);
    }

    @Override
    public List getCahche(String cacheName, HttpServletResponse var2) {
        return  cacheComponent.getCache(cacheName);
    }

    @Override
    public void addCahche(String cacheName, String value) {
        cacheComponent.addCache(cacheName, value);
    }
}
