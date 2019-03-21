package org.slj.service.impl;

import org.slj.dao.CampusCardOperationMapper;
import org.slj.domain.CampusCardOperation;
import org.slj.service.AbstractService;
import org.slj.service.CampusCardOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@create: 2019/3/14
*@Author: SLJ
*/
@Service
public class CampusCardOperationServiceImpl extends AbstractService<CampusCardOperation> implements CampusCardOperationService {

    @Autowired
    private CampusCardOperationMapper campusCardOperationMapper;

}
