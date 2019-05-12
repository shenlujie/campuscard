package org.slj.service.impl;

import org.slj.dao.PermissionMapper;
import org.slj.domain.Permission;
import org.slj.service.AbstractService;
import org.slj.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@create: 2019/5/12
 *@Author: SLJ
 */
@Service
public class PermissionServiceImpl extends AbstractService<Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
}
