package com.xiaocai.springboot.integration.cache.controller.impl;

import com.xiaocai.springboot.integration.cache.controller.UserController;
import com.xiaocai.springboot.integration.cache.entity.User;
import com.xiaocai.springboot.integration.cache.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/16 17:36
 */
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    @Autowired
    private UserMapper userMapper;

    @Override
    @RequestMapping("/getUserById")
    public User getUserById(@PathVariable(name = "id") int id) {
        return userMapper.selectUserById(id);
    }
}
