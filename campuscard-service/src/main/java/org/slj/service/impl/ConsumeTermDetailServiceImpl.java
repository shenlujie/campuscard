package org.slj.service.impl;

import org.slj.dao.ConsumeTermDetailMapper;
import org.slj.domain.ConsumeTermDetail;
import org.slj.service.AbstractService;
import org.slj.service.ConsumeTermDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@create: 2019/3/14
*@Author: SLJ
*/
@Service
public class ConsumeTermDetailServiceImpl extends AbstractService<ConsumeTermDetail> implements ConsumeTermDetailService {

    @Autowired
    private ConsumeTermDetailMapper consumeTermDetailMapper;

}
