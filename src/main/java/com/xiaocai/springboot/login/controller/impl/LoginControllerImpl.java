package com.xiaocai.springboot.login.controller.impl;

import com.xiaocai.springboot.login.controller.LoginController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/14 14:57
 */
@Controller
public class LoginControllerImpl implements LoginController {

    /*一种方式通过自定义一个 Controller 来进行转发：*/
    @Override
    @RequestMapping("/")
    public String login() {
        return "forward:login.html";    }
}
