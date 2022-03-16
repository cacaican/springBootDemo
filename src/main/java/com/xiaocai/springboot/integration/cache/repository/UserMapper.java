package com.xiaocai.springboot.integration.cache.repository;

import com.xiaocai.springboot.integration.cache.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@CacheConfig(cacheNames = "userCache")
@Component
public interface UserMapper {

    void insertUser(User user);

    @Cacheable
    User selectUserById(int id);

}