package com.xiaocai.springboot.integration.cache.controller.impl;

import com.xiaocai.springboot.integration.cache.component.RedisCacheComponent;
import com.xiaocai.springboot.integration.cache.controller.EhcacheController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/16 16:48
 */
@RestController("ehcacheController")
@RequestMapping({"/controller/cache/ehcache"})
public class EhcacheControllerImpl implements EhcacheController {

    @Autowired
    private EhCacheCacheComponent ehCacheCacheComponent;

    @Override
    public void removeCache(String cacheName, HttpServletResponse var2) {
        ehCacheCacheComponent.removeCache(cacheName);
    }

    @Override
    public List getCache(String cacheName, HttpServletResponse var2) {

        return  ehCacheCacheComponent.getCache(cacheName);
    }

    @Override
    public void addCache(String cacheName, String value) {
        ehCacheCacheComponent.addCache(cacheName,value);
    }

    @Override
    public Map<String, Object> scanCache(String cacheName) {
        return null;
    }
}
