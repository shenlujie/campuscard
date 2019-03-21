package org.slj.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slj.domain.FrontUserStudent;
import org.slj.service.FrontUserStudentService;
import org.slj.web.utils.json.MsgJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
*@Description: 前台学生用户
*@create: 2019/3/17
*@Author: SLJ
*/
@RestController
@RequestMapping("/frontUserStudent/")
public class FrontUserStudentController {

    /**
     * 日志工具
     */
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    FrontUserStudentService frontUserStudentService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add(FrontUserStudent frontUserStudent) {
        MsgJson msgJson;
        frontUserStudentService.saveModel(frontUserStudent);
        return "";
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String delete(@RequestParam Integer id) {
        MsgJson msgJson;
	    frontUserStudentService.deleteById(id);
	    return "";
    }

    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public String update(FrontUserStudent frontUserStudent) {
        MsgJson msgJson;
	    frontUserStudentService.update(frontUserStudent);
	    return "";
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(@RequestParam Integer id) {
        MsgJson msgJson;
        FrontUserStudent frontUserStudent = frontUserStudentService.findById(id);
        if (null == frontUserStudent){
            msgJson = MsgJson.not_found("无");
        }else {
            msgJson = MsgJson.success(frontUserStudent);
        }
        return msgJson.toJson();
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        MsgJson msgJson;
        PageHelper.startPage(page, size);
        List<FrontUserStudent> list = frontUserStudentService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return list.toString();
    }
}
