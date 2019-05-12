package org.slj.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slj.domain.CampusCardOperation;
import org.slj.enums.EmCode;
import org.slj.service.CampusCardOperationService;
import org.slj.service.FrontUserStudentService;
import org.slj.service.SendMessageService;
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

    /**
     * 业务处理状态
     */
    private static final String ORDER = "预约中";

    /**
     * 邮箱起始地址
     */
    private final String FROM = "18234175208@163.com";

    /**
     * 邮件标题
     */
    private final String TITLE = "校园一卡通业务进展通知";

    @Autowired
    CampusCardOperationService campusCardOperationService;

    @Autowired
    SendMessageService sendMessageService;

    @Autowired
    FrontUserStudentService frontUserStudentService;

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
    public String update(@RequestParam int id, @RequestParam String status) {
        CampusCardOperation campusCardOperation = campusCardOperationService.findById(id);
        if (ORDER.equals(status)){
            campusCardOperation.setStatus("办理中");
        }
        campusCardOperationService.update(campusCardOperation);
        MsgJson msgJson = MsgJson.success("正在办理");
        return msgJson.toJson();
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
        MsgJson msgJson = new MsgJson();
        msgJson.setSuccess(true)
                .setCode(EmCode.SUCCESS.getCode())
                .setMsg(EmCode.SUCCESS.getMsg())
                .setCount(list.size())
                .setObj(list);
        return msgJson.toJson();
    }

    @RequestMapping(value = "/sendMessage",method = RequestMethod.POST)
    public String sendMessage(@RequestParam int id,@RequestParam String apply){
        int stId = campusCardOperationService.findById(id).getStId();
        String to = frontUserStudentService.findById(stId).getStEmail();
        String content = "您申请办理的业务[" + apply + "],已经处理完毕，如是补办，请到一卡通电子中心领取.";
        boolean res = sendMessageService.sendMessage(FROM, to, TITLE, content);
        MsgJson msgJson = MsgJson.success(res);
        return msgJson.toJson();
    }
}
