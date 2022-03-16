package com.xiaocai.springboot.integration.cache.controller.impl;

import java.util.List;

public interface EhCacheCacheComponent {
    void removeCache(String cacheName);

    List getCache(String cacheName);

    void addCache(String cacheName, String value);
}
