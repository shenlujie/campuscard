package org.slj.service.impl;

import org.slj.dao.BackUserAdministratorMapper;
import org.slj.domain.BackUserAdministrator;
import org.slj.service.AbstractService;
import org.slj.service.BackUserAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
*@create: 2019/3/14
*@Author: SLJ
*/
@Service
public class BackUserAdministratorServiceImpl extends AbstractService<BackUserAdministrator> implements BackUserAdministratorService {

    @Autowired
    private BackUserAdministratorMapper backUserAdministratorMapper;

    @Override
    public BackUserAdministrator checkUsername(String username) {
        Condition condition = new Condition(BackUserAdministrator.class);
        condition.createCriteria().andEqualTo("adUsername", username);
        List<BackUserAdministrator> list = backUserAdministratorMapper.selectByCondition(condition);
        if (0 == list.size()){
            return null;
        }
        return list.get(0);
    }
}
