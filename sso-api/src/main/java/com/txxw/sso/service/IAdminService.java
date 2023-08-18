package com.txxw.sso.service;

import com.txxw.sso.dao.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.txxw.sso.vo.Result;
import com.txxw.sso.vo.params.LoginParam;

/**
 * <p>
 *  Admin服务类实现层
 * </p>
 *
 * @author Enci
 * @since 2023-08-15
 */
public interface IAdminService extends IService<Admin> {


    Admin getAdminByUserName(String username);

    /**
     * 保存用户
     *
     * @param admin
     */
    void saveUser(Admin admin);

}
