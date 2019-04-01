package org.slj.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slj.domain.CampusCardRule;
import org.slj.dto.UserMail;
import org.slj.dto.UserOperation;
import org.slj.enums.EmCode;
import org.slj.service.SendMessageService;
import org.slj.service.UserMailService;
import org.slj.service.UserOperationService;
import org.slj.web.utils.json.MsgJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 用户邮件controller
 * @create: 2019/4/1
 * @Author: SLJ
 */
@RestController
@RequestMapping("/userMail")
public class UserMailController {

    /**
     * 领导的邮箱
     */
    private static final String TO = "528805688@qq.com";

    /**
     * 邮箱起始地址
     */
    private final String FROM = "18234175208@163.com";

    @Autowired
    UserMailService userMailService;
    @Autowired
    SendMessageService sendMessageService;

    @RequestMapping(value = "/sendMessage",method = RequestMethod.POST)
    public String sendMessage(@RequestParam int id){
        UserMail userMail = userMailService.detail(id);

        String title = userMail.getTitle();
        String content = userMail.getContent() + "\n\n\n发信人：" + userMail.getStName() + "\n邮箱："+ userMail.getStEmail();
        boolean res = sendMessageService.sendMessage(FROM, TO, title, content);
        MsgJson msgJson = MsgJson.success(res);
        return msgJson.toJson();
    }

    @RequestMapping(value = "listByCondition",method = RequestMethod.GET)
    public String listByConditon(@RequestParam int status) {
        List<UserMail> list = userMailService.listByCondition(status);
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
