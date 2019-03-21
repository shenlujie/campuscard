package org.slj.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slj.domain.CampusCardAnnouncement;
import org.slj.service.CampusCardAnnouncementService;
import org.slj.web.utils.json.MsgJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
*@Description: 校园卡发布公告
*@create: 2019/3/17
*@Author: SLJ
*/
@RestController
@RequestMapping("/campusCardAnnouncement/")
public class CampusCardAnnouncementController {

    @Autowired
    CampusCardAnnouncementService campusCardAnnouncementService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add(CampusCardAnnouncement campusCardAnnouncement) {
        campusCardAnnouncementService.saveModel(campusCardAnnouncement);
        return "";
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String delete(@RequestParam Integer id) {
	    campusCardAnnouncementService.deleteById(id);
	    return "";
    }

    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public String update(CampusCardAnnouncement campusCardAnnouncement) {
	    campusCardAnnouncementService.update(campusCardAnnouncement);
	    return "";
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(@RequestParam Integer id) {
        MsgJson msgJson;
        CampusCardAnnouncement announcement = campusCardAnnouncementService.findById(id);
        if (null == announcement){
            msgJson = MsgJson.not_found("无");
        }else {
            msgJson = MsgJson.success(announcement);
        }
        return msgJson.toJson();
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<CampusCardAnnouncement> list = campusCardAnnouncementService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return list.toString();
    }
}
