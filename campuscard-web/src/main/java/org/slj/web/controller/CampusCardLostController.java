package org.slj.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slj.domain.CampusCardLost;
import org.slj.service.CampusCardLostService;
import org.slj.web.utils.json.MsgJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
*@Description: 校园卡失物招领
*@create: 2019/3/17
*@Author: SLJ
*/
@RestController
@RequestMapping("/campusCardLost/")
public class CampusCardLostController {

    @Autowired
    CampusCardLostService campusCardLostService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add(CampusCardLost campusCardLost) {
        campusCardLostService.saveModel(campusCardLost);
        return "";
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String delete(@RequestParam Integer id) {
	    campusCardLostService.deleteById(id);
	    return "";
    }

    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public String update(CampusCardLost campusCardLost) {
	    campusCardLostService.update(campusCardLost);
	    return "";
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(@RequestParam Integer id) {
        MsgJson msgJson;
        CampusCardLost lost = campusCardLostService.findById(id);
        if (null == lost){
            msgJson = MsgJson.not_found("无");
        }else {
            msgJson = MsgJson.success(lost);
        }
        return msgJson.toJson();
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<CampusCardLost> list = campusCardLostService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return list.toString();
    }
}
