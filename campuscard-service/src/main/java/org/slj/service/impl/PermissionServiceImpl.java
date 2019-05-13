package org.slj.service.impl;

import org.slj.dao.PermissionMapper;
import org.slj.domain.Permission;
import org.slj.service.AbstractService;
import org.slj.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 *@create: 2019/5/12
 *@Author: SLJ
 */
@Service
public class PermissionServiceImpl extends AbstractService<Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissionListByRoleId(int roleId) {
        Condition condition = new Condition(Permission.class);
        condition.createCriteria().andEqualTo("roleId", roleId);
        List<Permission> list = permissionMapper.selectByCondition(condition);
        return list;
    }
}
