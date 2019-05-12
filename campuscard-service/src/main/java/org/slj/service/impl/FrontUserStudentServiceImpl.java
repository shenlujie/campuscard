package org.slj.service.impl;

import org.slj.dao.FrontUserStudentMapper;
import org.slj.domain.FrontUserStudent;
import org.slj.service.AbstractService;
import org.slj.service.FrontUserStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
*@create: 2019/3/14
*@Author: SLJ
*/
@Service
public class FrontUserStudentServiceImpl extends AbstractService<FrontUserStudent> implements FrontUserStudentService {

    @Autowired
    private FrontUserStudentMapper frontUserStudentMapper;

    @Override
    public FrontUserStudent checkUsername(String stNum) {
        Condition condition = new Condition(FrontUserStudent.class);
        condition.createCriteria().andEqualTo("stNum", stNum);
        List<FrontUserStudent> list = frontUserStudentMapper.selectByCondition(condition);
        if (0 == list.size()){
            return null;
        }
        return list.get(0);
    }
}
