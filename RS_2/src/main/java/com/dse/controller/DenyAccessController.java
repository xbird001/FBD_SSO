package com.dse.controller;

import com.dse.dto.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DenyAccessController {


    @RequestMapping("/require/form")
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public SimpleResponse accessDeny() {
        return new SimpleResponse("没有权限，请引导用户先进行登录");
    }


}
