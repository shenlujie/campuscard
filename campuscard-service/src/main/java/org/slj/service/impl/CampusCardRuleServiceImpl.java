package org.slj.service.impl;

import org.slj.dao.CampusCardRuleMapper;
import org.slj.domain.CampusCardRule;
import org.slj.service.AbstractService;
import org.slj.service.CampusCardRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@create: 2019/3/14
 *@Author: SLJ
 */
@Service
public class CampusCardRuleServiceImpl extends AbstractService<CampusCardRule> implements CampusCardRuleService {

    @Autowired
    private CampusCardRuleMapper campusCardRuleMapper;

}
