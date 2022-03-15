package com.xiaocai.springboot.integration.springSecurity.controller.impl;

import com.xiaocai.springboot.integration.springSecurity.controller.SecurityController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/15 9:38
 */
@Controller
public class SecurityControllerImpl implements SecurityController {

    @GetMapping("/404")
    @Override
    public String notFoundPage() {
        return "404";
    }

    @GetMapping("/403")
    @Override
    public String accessError() {
        return "403";
    }

    @GetMapping("/500")
    @Override
    public String internalError() {
        return "500";
    }

    @GetMapping("/success")
    @ResponseBody
    @Override
    public String success(){
        return "认证成功,进入success成功";
    }

    @GetMapping(value = "/user/login")
    @Override
    public String loginPage(){
        return "login";
    }

    @GetMapping(value = "/person")
    public String personPage(){
        return "person";
    }

    @GetMapping(value = "/admin/index")
    public String adminPage(){
        return "admin/admin";
    }


}
