package com.txxw.sso.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.txxw.sso.dao.pojo.Test;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/8/11
 * @description:测试mapper
 **/
public interface TestMapper extends BaseMapper<Test> {
    Test findOneById(Long id);

}
