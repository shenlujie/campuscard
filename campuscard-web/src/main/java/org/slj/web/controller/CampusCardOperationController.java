package org.slj.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slj.domain.CampusCardOperation;
import org.slj.service.CampusCardOperationService;
import org.slj.web.utils.json.MsgJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
*@Description: 校园卡业务办理
*@create: 2019/3/17
*@Author: SLJ
*/
@RestController
@RequestMapping("/campusCardOperation/")
public class CampusCardOperationController {

    @Autowired
    CampusCardOperationService campusCardOperationService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add(CampusCardOperation campusCardOperation) {
        campusCardOperationService.saveModel(campusCardOperation);
        return "";
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String delete(@RequestParam Integer id) {
	    campusCardOperationService.deleteById(id);
	    return "";
    }

    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public String update(CampusCardOperation campusCardOperation) {
	    campusCardOperationService.update(campusCardOperation);
	    return "";
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(@RequestParam Integer id) {
        MsgJson msgJson;
        CampusCardOperation operation = campusCardOperationService.findById(id);
        if (null == operation){
            msgJson = MsgJson.not_found("无");
        }else {
            msgJson = MsgJson.success(operation);
        }
        return msgJson.toJson();
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<CampusCardOperation> list = campusCardOperationService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return list.toString();
    }
}
