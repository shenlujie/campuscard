package org.slj.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slj.domain.FrontUserStudent;
import org.slj.enums.EmCode;
import org.slj.service.FrontUserStudentService;
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
	    frontUserStudentService.update(frontUserStudent);
	    MsgJson msgJson = MsgJson.success("修改成功");
	    return msgJson.toJson();
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

    @RequestMapping(value = "listByCondition",method = RequestMethod.GET)
    public String listByCondition(@RequestParam(defaultValue = "0") Integer page
            , @RequestParam(defaultValue = "0") Integer limit
            , HttpServletRequest request){
        PageHelper.startPage(page, limit);
        Condition condition = new Condition(FrontUserStudent.class);
        Example.Criteria criteria = condition.createCriteria();
        String stNum = request.getParameter("stNum");
        String stName = request.getParameter("stName");
        String stDepartment = request.getParameter("stDepartment");
        String stMajor = request.getParameter("stMajor");
        if (!StringUtils.isEmpty(stNum)){
            criteria.andLike("stNum", "%" + stNum + "%");
        }
        if (!StringUtils.isEmpty(stName)){
            criteria.andLike("stName", "%" + stName + "%");
        }
        if (!StringUtils.isEmpty(stDepartment)){
            criteria.andLike("stDepartment", "%" + stDepartment + "%");
        }
        if (!StringUtils.isEmpty(stMajor)){
            criteria.andLike("stMajor", "%" + stMajor + "%");
        }
        List<FrontUserStudent> list = frontUserStudentService.findByCondition(condition);
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
