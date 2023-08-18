package com.txxw.sso.controller;


import com.txxw.sso.dao.pojo.Admin;
import com.txxw.sso.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Enci
 * @since 2023-08-15
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "获取当前登录用户的信息")
    @GetMapping("/info")
    public Admin getAdminInfo(Principal principal){
//        由于我们登录过之后已经把登录对象用户信息设置到Security全局中，因此可以直接使用Principal获取用户信息
        if (null==principal){
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);
//        admin.setRoles(adminService.getRoles(admin.getId()));
        return admin;
    }

}
