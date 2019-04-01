package org.slj.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slj.domain.CampusCardLost;
import org.slj.enums.EmCode;
import org.slj.service.CampusCardLostService;
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
        MsgJson msgJson = MsgJson.success("删除成功");
        return msgJson.toJson();
    }

    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public String update(@RequestParam int id, @RequestParam int status) {
        CampusCardLost campusCardLost = campusCardLostService.findById(id);
        if (status == 0){
            campusCardLost.setStatus(1);
        }
	    campusCardLostService.update(campusCardLost);
        MsgJson msgJson = MsgJson.success("推送成功");
	    return msgJson.toJson();
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

    @RequestMapping(value = "listByCondition",method = RequestMethod.GET)
    public String listByCondition(@RequestParam(defaultValue = "0") Integer page
            , @RequestParam(defaultValue = "0") Integer limit
            , HttpServletRequest request){
        PageHelper.startPage(page, limit);
        Condition condition = new Condition(CampusCardLost.class);
        Example.Criteria criteria = condition.createCriteria();
        String upStNum = request.getParameter("upStNum");
        String lostStNum = request.getParameter("lostStNum");
        String lostStName = request.getParameter("lostStName");
        if (!StringUtils.isEmpty(upStNum)){
            criteria.andLike("upStNum", "%" + upStNum + "%");
        }
        if (!StringUtils.isEmpty(lostStNum)){
            criteria.andLike("lostStNum", lostStNum + "%");
        }
        if (!StringUtils.isEmpty(lostStName)){
            criteria.andLike("lostStName", "%" + lostStName + "%");
        }
        List<CampusCardLost> list = campusCardLostService.findByCondition(condition);
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
