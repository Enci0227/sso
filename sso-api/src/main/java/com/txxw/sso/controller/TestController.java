package com.txxw.sso.controller;

import com.alibaba.fastjson.JSONObject;
import com.txxw.sso.common.aop.LogAnnotation;
import com.txxw.sso.service.TestService;
import com.txxw.sso.vo.Result;
import com.txxw.sso.vo.params.TestParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/8/10
 * @description:测试控制类
 **/
//json数据进行交互

@Api(description = "测试模块") //修饰整个类，描述Controller的作用
@RestController
@RequestMapping("test")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("远程请求发送")
    @PostMapping(value = "test1", produces = "application/json; charset=UTF-8")
    public Result test(HttpServletRequest request){
        // 获取第三方的authorization的token
        String authHeader = request.getHeader(tokenHeader);
        System.out.println(authHeader);
        //是否存在token,存在是否以tokenHead开头
        String authToken = authHeader.substring(tokenHead.length());
        String url = "http://127.0.0.1:8081/remote";

        HttpHeaders requestHeader=new HttpHeaders();
        // 将获取到的authorization添加到请求头
        requestHeader.add(tokenHeader,"Bearer "+authToken);
        // 构建请求实体
        HttpEntity<Object> requestEntity=new HttpEntity(null,requestHeader);
        // 使用restTemplate调用第三方接口
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        //         json转JSONObject
        JSONObject jsonObject = JSONObject.parseObject(response.getBody());
        Object obj = jsonObject.get("data");

        return Result.success(obj);
    }

    @ApiOperation("测试2")
    @PostMapping ("test2/{id}")
    public Result test2(@PathVariable("id") Long id){
        return testService.findOneById(id);
    }

    @ApiOperation("测试3")
    @ApiImplicitParams(
        {
            @ApiImplicitParam(
                    name = "id",
                    value = "用户id",
                    required = true,
                    dataType = "long",
                    paramType = "query"
            )
        }
    )
    @PostMapping ("test3")
    public Result test3(@RequestParam(value = "id",required = false) Long id){

        return testService.findById(id);
    }

    @ApiOperation("测试4")
    @PostMapping ("test4")
    //加上此注解 代表要对此接口记录日志
    @LogAnnotation(module="测试",operator="测试项目4")
    public Result test4(@RequestBody TestParam testParam){
        return Result.success(testParam);
    }

}
