package org.slj.service.impl;

import org.slj.dao.BackUserAdministratorMapper;
import org.slj.domain.BackUserAdministrator;
import org.slj.service.AbstractService;
import org.slj.service.BackUserAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@create: 2019/3/14
*@Author: SLJ
*/
@Service
public class BackUserAdministratorServiceImpl extends AbstractService<BackUserAdministrator> implements BackUserAdministratorService {

    @Autowired
    private BackUserAdministratorMapper backUserAdministratorMapper;



}
