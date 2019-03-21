package org.slj.service.impl;

import org.slj.dao.CampusCardLostMapper;
import org.slj.domain.CampusCardLost;
import org.slj.service.AbstractService;
import org.slj.service.CampusCardLostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@create: 2019/3/14
*@Author: SLJ
*/
@Service
public class CampusCardLostServiceImpl extends AbstractService<CampusCardLost> implements CampusCardLostService {

    @Autowired
    private CampusCardLostMapper campusCardLostMapper;

}
