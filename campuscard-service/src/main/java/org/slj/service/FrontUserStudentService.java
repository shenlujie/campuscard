package org.slj.service;

import org.slj.domain.FrontUserStudent;

/**
*@create: 2019/3/14
*@Author: SLJ
*/
public interface FrontUserStudentService extends Service<FrontUserStudent> {

    /**
     * 验证用户名是否存在
     * @param username 用户名
     * @return 一卡通用户实体类
     */
    FrontUserStudent checkUsername(String username);
}
