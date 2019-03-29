package org.slj.service;

import org.slj.domain.BackUserAdministrator;

/**
*@create: 2019/3/14
*@Author: SLJ
*/
public interface BackUserAdministratorService extends Service<BackUserAdministrator> {

    /**
     * 验证用户名是否存在
     * @param username 用户名
     * @return 管理员实体类
     */
    BackUserAdministrator checkUsername(String username);

}
