package org.slj.service.impl;

import org.slj.dao.RoleMapper;
import org.slj.domain.Role;
import org.slj.service.AbstractService;
import org.slj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@create: 2019/5/12
 *@Author: SLJ
 */
@Service
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
}
