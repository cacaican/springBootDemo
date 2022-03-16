package com.xiaocai.springboot.integration.cache.component;

import java.util.List;
import java.util.Map;

public interface RedisCacheComponent {
    void removeCache(String cacheName);

    List getCache(String cacheName);

    void addCache(String cacheName,String value);

    Map<String,Object> scanCache(String cacheName);
}
