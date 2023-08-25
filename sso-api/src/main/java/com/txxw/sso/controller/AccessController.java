package com.txxw.sso.controller;


import com.txxw.sso.dao.pojo.Access;
import com.txxw.sso.service.IAccessService;
import com.txxw.sso.service.LoginService;
import com.txxw.sso.vo.Result;
import com.txxw.sso.vo.params.LoginParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Enci
 * @since 2023-08-23
 */
@Api(tags = "AccessController")
@RestController
@RequestMapping("/access")
public class AccessController {

    @Autowired
    private IAccessService accessService;

    @ApiOperation(value = "获取项目访问链接")
    @GetMapping
    public Result findAllProjectUrl(){
        List<Access> list = accessService.list();
        return Result.success(list);
    }

}
