package org.slj.web.controller;

import org.slj.domain.CampusCardRule;
import org.slj.enums.EmCode;
import org.slj.service.CampusCardRuleService;
import org.slj.web.utils.json.MsgJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *@Description: 校园卡管理条例
 *@create: 2019/3/17
 *@Author: SLJ
 */
@RestController
@RequestMapping("/campusCardRule/")
public class CampusCardRuleController {

    @Autowired
    CampusCardRuleService campusCardRuleService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add(CampusCardRule campusCardRule) {
        campusCardRuleService.saveModel(campusCardRule);
        return "";
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String delete(@RequestParam Integer id) {
        campusCardRuleService.deleteById(id);
        MsgJson msgJson = MsgJson.success("删除成功");
        return msgJson.toJson();
    }

    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public String update(CampusCardRule campusCardRule) {
        campusCardRuleService.update(campusCardRule);
        MsgJson msgJson = MsgJson.success("删除成功");
        return msgJson.toJson();
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(@RequestParam Integer id) {
        MsgJson msgJson;
        CampusCardRule campusCardRule = campusCardRuleService.findById(id);
        if (null == campusCardRule){
            msgJson = MsgJson.not_found("无");
        }else {
            msgJson = MsgJson.success(campusCardRule);
        }
        return msgJson.toJson();
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list() {
        List<CampusCardRule> list = campusCardRuleService.findAll();
        MsgJson msgJson = new MsgJson();
        if (list.size() != 0){
            msgJson.setSuccess(true)
                    .setCode(EmCode.SUCCESS.getCode())
                    .setMsg(EmCode.SUCCESS.getMsg())
                    .setCount(list.size())
                    .setObj(list);
        }else {
            msgJson = MsgJson.not_found("没有数据");
        }

        return msgJson.toJson();
    }
}
