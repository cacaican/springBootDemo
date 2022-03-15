package com.xiaocai.springboot.integration.redis.component;

import java.util.List;

public interface CacheComponent {
    void removeCache(String cacheName);

    List getCache(String cacheName);

    void addCache(String cacheName,String value);
}
