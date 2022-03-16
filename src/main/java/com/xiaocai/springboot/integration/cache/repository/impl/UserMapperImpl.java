package com.xiaocai.springboot.integration.cache.repository.impl;

import com.xiaocai.springboot.integration.cache.entity.User;
import com.xiaocai.springboot.integration.cache.repository.UserMapper;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/16 17:33
 */
@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public void insertUser(User user) {

    }

    @Override
    public User selectUserById(int id) {
        return
               new User(id, "xiaocai")
                ;
    }
}
