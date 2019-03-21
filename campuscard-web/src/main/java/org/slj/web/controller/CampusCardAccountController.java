package org.slj.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slj.domain.CampusCardAccount;
import org.slj.service.CampusCardAccountService;
import org.slj.web.utils.json.MsgJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
*@Description: 校园卡账户
*@create: 2019/3/17
*@Author: SLJ
*/
@RestController
@RequestMapping("/campusCardAccount/")
public class CampusCardAccountController {

    @Autowired
    CampusCardAccountService campusCardAccountService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add(CampusCardAccount campusCardAccount) {
        campusCardAccountService.saveModel(campusCardAccount);
        return "";
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String delete(@RequestParam Integer id) {
	    campusCardAccountService.deleteById(id);
	    return "";
    }

    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public String update(CampusCardAccount campusCardAccount) {
	    campusCardAccountService.update(campusCardAccount);
	    return "";
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(@RequestParam Integer id) {
        MsgJson msgJson;
        CampusCardAccount account = campusCardAccountService.findById(id);
        if (null == account){
            msgJson = MsgJson.not_found("无");
        }else {
            msgJson = MsgJson.success(account);
        }
        return msgJson.toJson();
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<CampusCardAccount> list = campusCardAccountService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return list.toString();
    }
}
