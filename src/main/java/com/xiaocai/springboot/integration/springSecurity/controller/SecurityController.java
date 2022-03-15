package com.xiaocai.springboot.integration.springSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public interface SecurityController {


    @GetMapping("/404")
    String notFoundPage();

    @GetMapping("/403")
    String accessError();

    @GetMapping("/500")
    String internalError();

    @GetMapping("/success")
    @ResponseBody
    String success();

    @GetMapping(value = "/user/login")
    String loginPage();
}
