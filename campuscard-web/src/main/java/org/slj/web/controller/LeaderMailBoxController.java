package org.slj.web.controller;

import org.slj.domain.LeaderMailBox;
import org.slj.enums.EmCode;
import org.slj.service.LeaderMailBoxService;
import org.slj.web.utils.json.MsgJson;
import org.slj.web.utils.sensitive.SensitiveFilter;
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
 *@Description: 领导信箱controller
 *@create: 2019/3/17
 *@Author: SLJ
 */
@RestController
@RequestMapping("/leaderMailBox/")
public class LeaderMailBoxController {


    @Autowired
    LeaderMailBoxService leaderMailBoxService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add(LeaderMailBox leaderMailBox){
        String title = leaderMailBox.getTitle();
        String content = leaderMailBox.getContent();

        SensitiveFilter filter = new SensitiveFilter();

        // 进行过滤
        String titleFilted = filter.filter(title, '*');
        String contentFilted = filter.filter(content, '*');

        if (title != titleFilted){
            leaderMailBox.setStatus(0);
            leaderMailBox.setTitle(titleFilted);
        }else if (content != contentFilted){
            leaderMailBox.setStatus(0);
            leaderMailBox.setContent(contentFilted);
        }else {
            leaderMailBox.setStatus(1);
        }

        leaderMailBoxService.saveModel(leaderMailBox);
        MsgJson msgJson = MsgJson.success("提交审核");
        return msgJson.toJson();
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String delete(@RequestParam Integer id) {
        leaderMailBoxService.deleteById(id);
        MsgJson msgJson = MsgJson.success("删除成功");
        return msgJson.toJson();
    }

    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public String update(LeaderMailBox leaderMailBox) {
        leaderMailBoxService.update(leaderMailBox);
        MsgJson msgJson = MsgJson.success("删除成功");
        return msgJson.toJson();
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(@RequestParam Integer id) {
        MsgJson msgJson;
        LeaderMailBox leaderMailBox = leaderMailBoxService.findById(id);
        if (null == leaderMailBox){
            msgJson = MsgJson.not_found("无");
        }else {
            msgJson = MsgJson.success(leaderMailBox);
        }
        return msgJson.toJson();
    }

    @RequestMapping(value = "listByCondition",method = RequestMethod.GET)
    public String listByCondition(HttpServletRequest request) {
        Condition condition = new Condition(LeaderMailBox.class);
        Example.Criteria criteria = condition.createCriteria();
        String stId = request.getParameter("id");
        if (!StringUtils.isEmpty(stId)) {
            criteria.andEqualTo("stId", stId);
        }
        List<LeaderMailBox> list = leaderMailBoxService.findByCondition(condition);
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
