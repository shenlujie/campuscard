package org.slj.service.impl;

import org.slj.dao.CampusCardAccountMapper;
import org.slj.domain.CampusCardAccount;
import org.slj.service.AbstractService;
import org.slj.service.CampusCardAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@create: 2019/3/14
*@Author: SLJ
*/
@Service
public class CampusCardAccountServiceImpl extends AbstractService<CampusCardAccount> implements CampusCardAccountService {

    @Autowired
    private CampusCardAccountMapper campusCardAccountMapper;

}
