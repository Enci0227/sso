package com.txxw.sso.controller;

import com.txxw.sso.service.LoginService;
import com.txxw.sso.vo.Result;
import com.txxw.sso.vo.params.LoginParam;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/8/16
 * @description:用户注册接口
 **/
@Api(tags = "RegisterController")
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result register(@RequestBody LoginParam loginParam){
        //sso 单点登录，后期如果把登录注册功能 提出去（单独的服务，可以独立提供接口服务）
        return loginService.register(loginParam);
    }
}
