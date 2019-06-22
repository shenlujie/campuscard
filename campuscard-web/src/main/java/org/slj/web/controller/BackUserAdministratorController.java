package org.slj.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slj.domain.BackUserAdministrator;
import org.slj.enums.EmCode;
import org.slj.service.BackUserAdministratorService;
import org.slj.web.utils.json.MsgJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
*@Description: 后台管理员用户
*@create: 2019/3/17
*@Author: SLJ
*/
@RestController
@RequestMapping("/backUserAdministrator/")
public class BackUserAdministratorController {

    /**
     * 日志工具
     */
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    BackUserAdministratorService backUserAdministratorService;

    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public String update(BackUserAdministrator backUserAdministrator) {
	    backUserAdministratorService.update(backUserAdministrator);
	    MsgJson msgJson = MsgJson.success("修改成功");
	    return msgJson.toJson();
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(@RequestParam Integer id) {
        MsgJson msgJson;
        BackUserAdministrator administrator = backUserAdministratorService.findById(id);
        if (null == administrator){
            msgJson = MsgJson.notFound("无");
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

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password
    , HttpSession session){
        MsgJson msgJson = new MsgJson();
        BackUserAdministrator backUserAdministrator = backUserAdministratorService.checkUsername(username);
        if (null == backUserAdministrator){
            msgJson.setSuccess(false).setCode(EmCode.SUCCESS.getCode()).setMsg("该用户不存在");
        }else if (!password.equals(backUserAdministrator.getAdPassword())){
            msgJson.setSuccess(false).setCode(EmCode.SUCCESS.getCode()).setMsg("密码不正确");
        }else {
            msgJson.setSuccess(true);
            session.setAttribute("backUserAdministrator", backUserAdministrator);
        }
        return msgJson.toJson();
    }
}
