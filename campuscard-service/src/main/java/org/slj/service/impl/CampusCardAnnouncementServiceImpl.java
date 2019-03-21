package org.slj.service.impl;

import org.slj.dao.CampusCardAnnouncementMapper;
import org.slj.domain.CampusCardAnnouncement;
import org.slj.service.AbstractService;
import org.slj.service.CampusCardAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@create: 2019/3/14
*@Author: SLJ
*/
@Service
public class CampusCardAnnouncementServiceImpl extends AbstractService<CampusCardAnnouncement> implements CampusCardAnnouncementService {

    @Autowired
    private CampusCardAnnouncementMapper campusCardAnnouncementMapper;

}
