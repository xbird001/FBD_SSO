package com.dse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/user/me")
    public String getUser() {
        return "我是资源【2】服务器的资源";
    }

}

