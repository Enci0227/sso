package com.txxw.sso.controller;

import com.txxw.sso.common.aop.LogAnnotation;
import com.txxw.sso.service.LoginService;
import com.txxw.sso.vo.Result;
import com.txxw.sso.vo.params.TestParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.txxw.sso.vo.ErrorCode.TOKEN_ERROR;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/8/19
 * @description:
 **/
@Api(tags = "TokenController")
@RestController
@RequestMapping("token")
public class TokenController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private LoginService loginService;

    @ApiOperation("token校验")
    @PostMapping()
    //加上此注解 代表要对此接口记录日志，其他站点访问此接口验证token是否有效
//    @LogAnnotation(module="TokenController",operator="token校验")
    public Result checkToken(HttpServletRequest request) {
        String authHeader = request.getHeader(tokenHeader);
        //是否存在token,存在是否以tokenHead开头
        String authToken = authHeader.substring(tokenHead.length());

        return loginService.checkToken(authToken);
    }

}
