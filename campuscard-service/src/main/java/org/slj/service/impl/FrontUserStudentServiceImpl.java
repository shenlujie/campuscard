package org.slj.service.impl;

import org.slj.dao.FrontUserStudentMapper;
import org.slj.domain.FrontUserStudent;
import org.slj.service.AbstractService;
import org.slj.service.FrontUserStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@create: 2019/3/14
*@Author: SLJ
*/
@Service
public class FrontUserStudentServiceImpl extends AbstractService<FrontUserStudent> implements FrontUserStudentService {

    @Autowired
    private FrontUserStudentMapper frontUserStudentMapper;

}
