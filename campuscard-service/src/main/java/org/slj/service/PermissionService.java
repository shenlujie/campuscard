package org.slj.service;

import org.slj.domain.Permission;

import java.util.List;

/**
 *@create: 2019/5/12
 *@Author: SLJ
 */
public interface PermissionService extends Service<Permission> {
    /**
     * 根据role id获取学生用户的权限列表
     * @param roleId 角色id
     * @return 该角色的所有权限
     */
    List<Permission> getPermissionListByRoleId(int roleId);
}
