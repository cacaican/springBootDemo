package com.xiaocai.springboot.integration.cache.component.impl;

import com.xiaocai.springboot.integration.cache.controller.impl.EhCacheCacheComponent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/16 16:53
 */
@Component
public class EhCacheCacheComponentImpl implements EhCacheCacheComponent {
    @Override
    public void removeCache(String cacheName) {

    }

    @Override
    public List getCache(String cacheName) {
        return null;
    }

    @Override
    public void addCache(String cacheName, String value) {

    }
}
