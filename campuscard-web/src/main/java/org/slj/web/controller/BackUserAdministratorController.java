package org.slj.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slj.domain.BackUserAdministrator;
import org.slj.service.BackUserAdministratorService;
import org.slj.web.utils.json.MsgJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
*@Description: 后台管理员用户
*@create: 2019/3/17
*@Author: SLJ
*/
@RestController
@RequestMapping("/backUserAdministrator/")
public class BackUserAdministratorController {

    @Autowired
    BackUserAdministratorService backUserAdministratorService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add(BackUserAdministrator backUserAdministrator) {
        backUserAdministratorService.saveModel(backUserAdministrator);
        return "";
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String delete(@RequestParam Integer id) {
	    backUserAdministratorService.deleteById(id);
	    return "";
    }

    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public String update(BackUserAdministrator backUserAdministrator) {
	    backUserAdministratorService.update(backUserAdministrator);
	    return "";
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(@RequestParam Integer id) {
        MsgJson msgJson;
        BackUserAdministrator administrator = backUserAdministratorService.findById(id);
        if (null == administrator){
            msgJson = MsgJson.not_found("无");
        }else {
            msgJson = MsgJson.success(administrator);
        }
        return msgJson.toJson();
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<BackUserAdministrator> list = backUserAdministratorService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return list.toString();
    }
}
