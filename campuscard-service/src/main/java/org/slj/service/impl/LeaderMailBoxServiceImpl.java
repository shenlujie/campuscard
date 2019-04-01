package org.slj.service.impl;

import org.slj.dao.LeaderMailBoxMapper;
import org.slj.domain.LeaderMailBox;
import org.slj.service.AbstractService;
import org.slj.service.LeaderMailBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@create: 2019/3/14
 *@Author: SLJ
 */
@Service
public class LeaderMailBoxServiceImpl extends AbstractService<LeaderMailBox> implements LeaderMailBoxService {

    @Autowired
    private LeaderMailBoxMapper leaderMailBoxMapper;

}
