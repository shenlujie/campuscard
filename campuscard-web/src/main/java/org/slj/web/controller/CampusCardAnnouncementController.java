package org.slj.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slj.domain.BackUserAdministrator;
import org.slj.domain.CampusCardAnnouncement;
import org.slj.enums.EmCode;
import org.slj.service.CampusCardAnnouncementService;
import org.slj.web.utils.json.MsgJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
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

    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public String update(CampusCardAnnouncement campusCardAnnouncement) {
        campusCardAnnouncementService.update(campusCardAnnouncement);
        return "";
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String delete(@RequestParam Integer id) {
	    campusCardAnnouncementService.deleteById(id);
	    return "";
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add(HttpServletRequest request) {
        CampusCardAnnouncement announcement = new CampusCardAnnouncement();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        announcement.setAdId(1);
        announcement.setReleaseTime(new Date());
        announcement.setTitle(title);
        announcement.setDetails(content);
	    campusCardAnnouncementService.saveModel(announcement);
	    MsgJson msgJson = MsgJson.success("添加成功");
	    return msgJson.toJson();
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
    public String listByCondition(@RequestParam(defaultValue = "0") Integer page
            , @RequestParam(defaultValue = "0") Integer limit){
        PageHelper.startPage(page, limit);
        List<CampusCardAnnouncement> list = campusCardAnnouncementService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        MsgJson msgJson = new MsgJson();
        msgJson.setSuccess(true)
                .setCode(EmCode.SUCCESS.getCode())
                .setMsg(EmCode.SUCCESS.getMsg())
                .setCount((int)pageInfo.getTotal())
                .setObj(list);
        return msgJson.toJson();
    }
}
