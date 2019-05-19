package org.slj.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slj.domain.ConsumeTermDetail;
import org.slj.domain.FrontUserStudent;
import org.slj.enums.EmCode;
import org.slj.service.ConsumeTermDetailService;
import org.slj.web.utils.json.MsgJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
*@Description: 校园卡消费明细
*@create: 2019/3/17
*@Author: SLJ
*/
@RestController
@RequestMapping("/consumeTermDetail/")
public class ConsumeTermDetailController {

    /**
     * 日志工具
     */
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    ConsumeTermDetailService consumeTermDetailService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add(ConsumeTermDetail consumeTermDetail) {
        consumeTermDetailService.saveModel(consumeTermDetail);
        return "";
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String delete(@RequestParam Integer id) {
	    consumeTermDetailService.deleteById(id);
	    MsgJson msgJson = MsgJson.success("删除成功");
	    return msgJson.toJson();
    }

    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public String update(ConsumeTermDetail consumeTermDetail) {
	    consumeTermDetailService.update(consumeTermDetail);
	    return "";
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(@RequestParam Integer id) {
        MsgJson msgJson;
        ConsumeTermDetail termDetail = consumeTermDetailService.findById(id);
        if (null == termDetail){
            msgJson = MsgJson.not_found("无");
        }else {
            msgJson = MsgJson.success(termDetail);
        }
        return msgJson.toJson();
    }

    @RequestMapping(value = "listByCondition",method = RequestMethod.GET)
    public String listByCondition(@RequestParam(defaultValue = "0") Integer page
            , @RequestParam(defaultValue = "0") Integer limit
            , HttpServletRequest request){
        PageHelper.startPage(page, limit);
        Condition condition = new Condition(ConsumeTermDetail.class);
        Example.Criteria criteria = condition.createCriteria();
        String stNum = request.getParameter("stNum");
        String happenTime = request.getParameter("happenTime");
        String consumeRecharge = request.getParameter("consumeRecharge");
        String sum = request.getParameter("sum");
        if (!StringUtils.isEmpty(stNum)){
            criteria.andLike("stNum", "%" + stNum + "%");
        }
        if (!StringUtils.isEmpty(happenTime)){
            criteria.andLike("happenTime", happenTime + "%");
        }
        if (!StringUtils.isEmpty(consumeRecharge)){
            criteria.andLike("consumeRecharge", "%" + consumeRecharge + "%");
        }
        if (!StringUtils.isEmpty(sum)){
            criteria.andEqualTo("sum", sum);
        }
        List<ConsumeTermDetail> list = consumeTermDetailService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        MsgJson msgJson = new MsgJson();
        msgJson.setSuccess(true)
                .setCode(EmCode.SUCCESS.getCode())
                .setMsg(EmCode.SUCCESS.getMsg())
                .setCount((int)pageInfo.getTotal())
                .setObj(list);
        return msgJson.toJson();
    }

    @RequestMapping(value = "listById",method = RequestMethod.GET)
    public String listById(@RequestParam(defaultValue = "0") Integer page
            , @RequestParam(defaultValue = "0") Integer limit
            , HttpServletRequest request){
        PageHelper.startPage(page, limit);
        Condition condition = new Condition(ConsumeTermDetail.class);
        Example.Criteria criteria = condition.createCriteria();
        String stNum = request.getParameter("stNum");
        if (!StringUtils.isEmpty(stNum)){
            criteria.andEqualTo("stNum", stNum);
        }
        List<ConsumeTermDetail> list = consumeTermDetailService.findByCondition(condition);
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
