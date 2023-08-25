package com.txxw.sso.service;

import com.txxw.sso.dao.pojo.Admin;
import com.txxw.sso.vo.Result;
import com.txxw.sso.vo.params.LoginParam;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/8/15
 * @description:
 **/
public interface LoginService {
    /**
     * 登录之后返回token
     * @param adminLoginParam
     * @return
     */
    Result login(LoginParam adminLoginParam);

    /**
     * 退出登录
     * @return
     */
    Result logout();

    /**
     * 注册
     * @param loginParam
     * @return
     */
    Result register(LoginParam loginParam);

    Result checkToken(String token);
}
